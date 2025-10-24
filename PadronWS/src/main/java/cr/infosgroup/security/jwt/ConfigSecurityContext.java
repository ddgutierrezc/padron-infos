package cr.infosgroup.security.jwt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import cr.infosgroup.padron.ws.properties.CorsProperties;
import cr.infosgroup.security.jwt.auth.AuthProvider;
import cr.infosgroup.security.jwt.auth.UserDetailsServices;
import cr.infosgroup.security.jwt.auth.custom.liferay.AuthenticationProviderLiferay;
import cr.infosgroup.security.jwt.auth.custom.liferay.UserDetailsServiceLiferay;
import cr.infosgroup.security.jwt.auth.custom.prueba.AuthenticationProviderPrueba;
import cr.infosgroup.security.jwt.auth.custom.prueba.UserDetailsServicePrueba;
import cr.infosgroup.security.jwt.entry.AuthenticationEntryPointJWT;
import cr.infosgroup.security.jwt.filter.AuthenticationFilterJWT;
import cr.infosgroup.security.jwt.services.LoginAttemptService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@PropertySource("classpath:project.properties")
@ComponentScan(basePackages = {"cr.infosgroup.security.jwt"})
public class ConfigSecurityContext {

    private final CorsProperties corsProperties;
    
    private final String apiRoot;
    
    private final UserDetailsServices userDetailsServices;
    
    @Autowired
    private AuthenticationProviderPrueba authenticationProviderPrueba;
    
    @Autowired
    private AuthenticationProviderLiferay authenticationProviderLiferay;
    

    public ConfigSecurityContext(
            CorsProperties corsProperties,
            @Value("${project.apiRoot}") String apiRoot,
            UserDetailsServicePrueba userDetailsServicePrueba,
            UserDetailsServiceLiferay userDetailsServiceLiferay
    ) {
        this.corsProperties = corsProperties;
        this.apiRoot = apiRoot;
        
    	Map<AuthProvider, UserDetailsService> userDetailsServices = new HashMap<>();
    	userDetailsServices.put(AuthProvider.PRUEBA, userDetailsServicePrueba);
    	userDetailsServices.put(AuthProvider.LIFERAY, userDetailsServiceLiferay);
    	this.userDetailsServices = new UserDetailsServices(userDetailsServices);
    }

    // BEANS
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public LoginAttemptService loginAttemptService() {
        return new LoginAttemptService();
    }

    @Bean
    public AuthenticationEntryPointJWT authenticationEntryPointJWT() {
        return new AuthenticationEntryPointJWT();
    }

    @Bean
    public AuthenticationFilterJWT authenticationFilterJWT(ISeguridadJWT seguridadJWT) {
        return new AuthenticationFilterJWT(seguridadJWT,this.userDetailsServices);
    }
    
    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providers = List.of(
            authenticationProviderPrueba,     
            authenticationProviderLiferay 
        );
        return new ProviderManager(providers);
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationFilterJWT authenticationFilterJWT) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .exceptionHandling(ex -> ex.authenticationEntryPoint(this.authenticationEntryPointJWT()))
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(
                		this.apiRoot+"/security/about", 
                		this.apiRoot+"/security/auth"
                		).permitAll()
                .requestMatchers(
                		this.apiRoot+"/**"
                		).authenticated()
            )
            .addFilterBefore(authenticationFilterJWT, UsernamePasswordAuthenticationFilter.class)
            .headers(this::configureSecurityHeaders);
        return http.build();
    }

    private void configureSecurityHeaders(HeadersConfigurer<HttpSecurity> headers) {
        headers.defaultsDisabled();
        headers.addHeaderWriter((request, response) -> {
            response.setHeader("X-Content-Type-Options", "nosniff");
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            response.setHeader("Referrer-Policy", "no-referrer");
            response.setHeader("Permissions-Policy", "geolocation=(), microphone=()");
            response.setHeader("Content-Security-Policy", "default-src 'self'; script-src 'self'; object-src 'none'");
            response.setHeader("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains"); 
        });
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(this.corsProperties.getAllowedOrigins());
        config.setAllowedMethods(this.corsProperties.getAllowedMethods());
        config.setAllowedHeaders(this.corsProperties.getAllowedHeaders());
        config.setExposedHeaders(this.corsProperties.getExposedHeaders());
        config.setAllowCredentials(this.corsProperties.getAllowCredentials());
        config.setMaxAge(this.corsProperties.getMaxAge());
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(this.apiRoot + "/**", config);
        return source;
    }
}