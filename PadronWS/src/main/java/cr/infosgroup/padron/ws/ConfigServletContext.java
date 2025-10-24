package cr.infosgroup.padron.ws;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cr.infosgroup.padron.ws.rest.ctl") //scan de ctl
public class ConfigServletContext implements BeanFactoryPostProcessor, WebMvcConfigurer {

    private static final Logger log = LogManager.getLogger(ConfigServletContext.class);
	
    private String apiRoot;
    private String basePackageCtl;
    private String[] allowedOrigins;
    private String[] allowedMethods;
    private String[] allowedHeaders;
    private String[] exposedHeaders;
    private Boolean allowCredentials;
    private long maxAge; 
	
	@Bean
	public Properties projectProperties() throws IOException {
	    try (InputStream in = getClass().getClassLoader().getResourceAsStream("project.properties")) {
	        if (in == null) {
	            throw new IOException("No se encontró project.properties en el classpath");
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
			this.apiRoot = props.getProperty("project.apiRoot");
			this.basePackageCtl = props.getProperty("project.basePackageCtl");
			
			String allowedOrigins = props.getProperty("project.cors.allowedOrigins");
			this.allowedOrigins = allowedOrigins.split(",");
			String allowedMethods = props.getProperty("project.cors.allowedMethods");
			this.allowedMethods = allowedMethods.split(",");
			String allowedHeaders = props.getProperty("project.cors.allowedHeaders");
			this.allowedHeaders = allowedHeaders.split(",");
			String exposedHeaders = props.getProperty("project.cors.exposedHeaders");
			this.exposedHeaders = exposedHeaders.split(",");
			String allowCredentials = props.getProperty("project.cors.allowCredentials");
			this.allowCredentials = allowCredentials.equalsIgnoreCase("true");
		    String maxAge = props.getProperty("project.cors.maxAge");
			this.maxAge = Long.valueOf(maxAge);
			
			//Scanea base
			ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner((BeanDefinitionRegistry) beanFactory);
			scanner.scan(this.basePackageCtl);
		
			log.info("INICIADO ServletContextConfig se scanea {}",this.basePackageCtl);
		} 
		catch (IOException e) {
			log.warn("Error al cargar project.properties en ServletContextConfig", e);
		}
	}
    
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper;
    }
    
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper()));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(this.apiRoot+"/*")
                .allowedOrigins(this.allowedOrigins)
                .allowedMethods(this.allowedMethods)
                .allowedHeaders(this.allowedHeaders)
                .exposedHeaders(this.exposedHeaders)
                .allowCredentials(this.allowCredentials)
                .maxAge(this.maxAge);
    }
}