package cr.infosgroup.security.jwt.auth;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Contiene los UserDetailsService soportados en la autenticacion
 * @author lsanabria
 */
public class UserDetailsServices {
	
    private final Map<AuthProvider, UserDetailsService> userDetailsServices;
    
    public UserDetailsServices(Map<AuthProvider, UserDetailsService> userDetailsServices) {
    	this.userDetailsServices = userDetailsServices; 
    }
    
    public UserDetails loadUserByUsername(String username, AuthProvider provider) throws Exception {
        UserDetailsService userDetailService = this.userDetailsServices.get(provider);
        if (userDetailService == null) {
            throw new Exception("Proveedor de autenticación no soportado: " + provider);
        }
        return userDetailService.loadUserByUsername(username);
    }
}