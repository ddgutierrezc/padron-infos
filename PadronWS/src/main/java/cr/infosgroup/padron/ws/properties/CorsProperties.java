package cr.infosgroup.padron.ws.properties;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:project.properties")
public class CorsProperties {
	
    @Value("${project.cors.allowedOrigins}")
    private List<String> allowedOrigins;

    @Value("${project.cors.allowedMethods}")
    private List<String> allowedMethods;

    @Value("${project.cors.allowedHeaders}")
    private List<String> allowedHeaders;

    @Value("${project.cors.exposedHeaders}")
    private List<String> exposedHeaders;

    @Value("${project.cors.allowCredentials}")
    private Boolean allowCredentials;

    @Value("${project.cors.maxAge}")
    private Long maxAge;

	public List<String> getAllowedOrigins() {
		return allowedOrigins;
	}

	public void setAllowedOrigins(List<String> allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}

	public List<String> getAllowedMethods() {
		return allowedMethods;
	}

	public void setAllowedMethods(List<String> allowedMethods) {
		this.allowedMethods = allowedMethods;
	}

	public List<String> getAllowedHeaders() {
		return allowedHeaders;
	}

	public void setAllowedHeaders(List<String> allowedHeaders) {
		this.allowedHeaders = allowedHeaders;
	}

	public List<String> getExposedHeaders() {
		return exposedHeaders;
	}

	public void setExposedHeaders(List<String> exposedHeaders) {
		this.exposedHeaders = exposedHeaders;
	}

	public Boolean getAllowCredentials() {
		return allowCredentials;
	}

	public void setAllowCredentials(Boolean allowCredentials) {
		this.allowCredentials = allowCredentials;
	}

	public Long getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Long maxAge) {
		this.maxAge = maxAge;
	}
}
