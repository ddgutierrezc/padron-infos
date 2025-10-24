package cr.infosgroup.padron.bl;

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
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cr.infosgroup.padron.dal.audit.AuditorAwareImpl;
import cr.infosgroup.padron.dal.audit.ContextAwareAuditoriaProvider;
import cr.infosgroup.padron.dal.audit.IAuditoriaProvider;

@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ConfigApplicationContextBL implements BeanFactoryPostProcessor {
	
    private static final Logger log = LogManager.getLogger(ConfigApplicationContextBL.class);
    
	private String basePackage;
	private String basePackageEntity;
	
	@Bean
	public Properties projectProperties() throws IOException {
		String nombreArchivoPropiedades = "projectBL.properties";
		 
	    try (InputStream in = getClass().getClassLoader().getResourceAsStream(nombreArchivoPropiedades)) {
	        if (in == null) {
	            throw new IOException("No se encontró "+ nombreArchivoPropiedades + " en el classpath de BL");
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
			this.basePackage = props.getProperty("project.basePackage");
			this.basePackageEntity =  props.getProperty("project.basePackageEntity"); 
			
			//Escaner
			ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner((BeanDefinitionRegistry) beanFactory);
			scanner.scan(this.basePackage);
		
			log.info("INICIADO ConfigApplicationContextBL se scanea {}",this.basePackage);
		} 
		catch (IOException e) {
			log.warn("Error al cargar ConfigApplicationContextBL", e);
		}
	}	
    
    // DataSource
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.mariadb.jdbc.Driver");
        ds.setUrl("jdbc:mariadb://localhost:3306/padron");
        ds.setUsername("padron");
        ds.setPassword("padron");
        return ds;
    }

    // EntityManagerFactory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan(this.basePackageEntity);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(false);
        emf.setJpaVendorAdapter(vendorAdapter);

        java.util.Properties jpaProps = new java.util.Properties();
        jpaProps.put("hibernate.hbm2ddl.auto", "update");
        emf.setJpaProperties(jpaProps);

        return emf;
    }

    // TransactionManager
    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean emf) {
        JpaTransactionManager tx = new JpaTransactionManager();
        tx.setEntityManagerFactory(emf.getObject());
        return tx;
    }    
    
    @Bean
    public AuditorAware<String> auditorAware(@Qualifier("contextAwareAuditoriaProvider") IAuditoriaProvider auditoriaProvider) {
        return new AuditorAwareImpl(auditoriaProvider);
    }

    @Bean(name = "contextAwareAuditoriaProvider")
    public IAuditoriaProvider contextAwareAuditoriaProvider(
        @Qualifier("auditoriaProviderBL") IAuditoriaProvider auditoriaProviderBL
    ) {
        return new ContextAwareAuditoriaProvider(auditoriaProviderBL);
    }      
    
    //TODO: implementar esto con MapStruct que es mas eficiente
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
