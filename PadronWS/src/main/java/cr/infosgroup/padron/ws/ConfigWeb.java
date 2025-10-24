package cr.infosgroup.padron.ws;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import cr.infosgroup.security.jwt.ConfigSecurityContext;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

/**
 * Configuración principal de la aplicación web.
 * <p>
 * Inicializa el DispatcherServlet y el contexto raíz,
 * además de registrar el filtro de seguridad de Spring Security
 * para los endpoints bajo {@code apiRoot}.
 * </p>
 * 
 * Carga la propiedad {@code project.apiRoot} desde el archivo {@code project.properties}.
 * 
 * @author lsanabria
 */
public class ConfigWeb extends AbstractAnnotationConfigDispatcherServletInitializer {
	
    private static final Logger log = LogManager.getLogger(ConfigWeb.class);
	
    /** Raíz de la API obtenida desde project.properties */
    private String apiRoot;
    
    /**
     * Constructor.
     * <p>
     * Carga {@code project.apiRoot} desde el archivo de propiedades y lo registra en el log.
     * </p>
     */
    public ConfigWeb() {
    	try (InputStream in = getClass().getClassLoader().getResourceAsStream("project.properties")) {
    		Properties props = new Properties();
    		props.load(in);
    	    this.apiRoot = props.getProperty("project.apiRoot");
    	    log.info("INICIADO WebConfig con apiRoot={}", this.apiRoot);
    	} 
    	catch (IOException e) {
    	    log.warn("Error al cargar project.properties en WebConfig", e);
    	}	
    }
    
    /**
     * Configura los {@link org.springframework.context.ApplicationContext} raíz
     * de la aplicación.
     * <p>
     * Incluye beans de aplicación general y configuración de seguridad.
     * </p>
     * 
     * @return clases de configuración raíz
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
            ConfigApplicationContext.class,
            ConfigSecurityContext.class,
            ConfigServletContext.class
        };
    }
    
    /**
     * Configura el contexto del {@link org.springframework.web.servlet.DispatcherServlet}.
     * <p>
     * Incluye beans relacionados con MVC, Jackson, CORS, etc.
     * </p>
     * 
     * @return clases de configuración del servlet
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ConfigServletContext.class};
    }

    /**
     * Define el mapeo del {@link org.springframework.web.servlet.DispatcherServlet}.
     * 
     * @return mapeo del servlet
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    
    /**
     * Inicializa la aplicación web y registra filtros adicionales.
     * <p>
     * Se registra un {@link DelegatingFilterProxy} para Spring Security
     * apuntando a {@code springSecurityFilterChain} y se mapea
     * a todos los endpoints bajo {@code apiRoot}.
     * </p>
     * 
     * @param servletContext contexto del servlet
     * @throws ServletException si ocurre un error durante el arranque
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        FilterRegistration.Dynamic securityFilter = servletContext.addFilter(
                "springSecurityFilterChain",
                new DelegatingFilterProxy("springSecurityFilterChain")
        );
        securityFilter.addMappingForUrlPatterns(null, false, this.apiRoot + "/*");
        securityFilter.setAsyncSupported(true);
    }   
}
