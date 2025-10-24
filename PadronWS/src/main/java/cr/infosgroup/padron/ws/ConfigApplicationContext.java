package cr.infosgroup.padron.ws;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cr.infosgroup.integraciones.liferay.Liferay;
import cr.infosgroup.padron.dal.audit.AuditorAwareImpl;
import cr.infosgroup.padron.dal.audit.ContextAwareAuditoriaProvider;
import cr.infosgroup.padron.dal.audit.IAuditoriaProvider;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ConfigApplicationContext implements BeanFactoryPostProcessor {
	
    private static final Logger log = LogManager.getLogger(ConfigApplicationContext.class);
    
	private String version;
	private String basePackage;
	private String basePackageEntity;
    
	@Bean
	public Properties projectProperties() throws IOException {
		String nombreArchivoPropiedades = "project.properties";
	    try (InputStream in = getClass().getClassLoader().getResourceAsStream(nombreArchivoPropiedades)) {
	        if (in == null) {
	            throw new IOException("No se encontró "+ nombreArchivoPropiedades + " en el classpath de WS");
	        }
	        Properties props = new Properties();
	        props.load(in);
	        return props;
	    }
	}
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		try {
			//Archivo de propiedades
			Properties props = projectProperties();
			this.version = props.getProperty("project.version");
			this.basePackage = props.getProperty("project.basePackage");
			this.basePackageEntity =  props.getProperty("project.basePackageEntity"); 
			
			//Escaner
			ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner((BeanDefinitionRegistry) beanFactory);
			scanner.scan(this.basePackage);
		
			log.info("INICIADO ApplicationContextConfig versión {} se scanea {}",this.version,this.basePackage);
		} 
		catch (IOException e) {
			log.warn("Error al cargar ConfigApplicationContext", e);
		}
	}
	
    @Bean
    public String version() {
        return this.version;
    }

    @Bean
    public DataSource dataSource() throws Exception {
        try {
            JndiObjectFactoryBean jndiFactory = new JndiObjectFactoryBean();
            jndiFactory.setJndiName("java:comp/env/jdbc/padronDataSource");
            jndiFactory.setProxyInterface(DataSource.class);
            jndiFactory.afterPropertiesSet();
            return (DataSource) jndiFactory.getObject();
        } catch (Exception e) {
            log.error("No se pudo inicializar DataSource desde JNDI", e);
            throw e;
        }
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan(this.basePackageEntity);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(false);
        emf.setJpaVendorAdapter(vendorAdapter);

        Properties jpaProps = new Properties();
        jpaProps.setProperty("hibernate.hbm2ddl.auto", "update");//validate
        emf.setJpaProperties(jpaProps);

        return emf;
    }

    @Bean
    public AuditorAware<String> auditorAware(@Qualifier("contextAwareAuditoriaProvider") IAuditoriaProvider auditoriaProvider) {
        return new AuditorAwareImpl(auditoriaProvider);
    }

    @Bean(name = "contextAwareAuditoriaProvider")
    public IAuditoriaProvider contextAwareAuditoriaProvider(
    	@Qualifier("auditoriaProviderWS") IAuditoriaProvider auditoriaProviderWS,
        @Qualifier("auditoriaProviderBL") IAuditoriaProvider auditoriaProviderBL
    ) {
        return new ContextAwareAuditoriaProvider(auditoriaProviderWS,auditoriaProviderBL);
    }     
    
    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean emf) {
        JpaTransactionManager tx = new JpaTransactionManager();
        tx.setEntityManagerFactory(emf.getObject());
        return tx;
    }

    //TODO: implementar esto con MapStruct que es mas eficiente
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
	@Bean
	public Liferay liferay() {
		String urlRootLiferay = "http://localhost:8080";
		String userLiferay = "usrSistema@liferay.com";
		String passwordLiferay = "infos";
		long companyId = 20097L;
		String authType = "emailAddress";
		return new Liferay(urlRootLiferay, userLiferay, passwordLiferay, companyId, authType);
	}
    
}
