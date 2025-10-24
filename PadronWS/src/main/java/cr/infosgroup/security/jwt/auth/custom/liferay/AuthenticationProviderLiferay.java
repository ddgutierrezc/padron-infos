package cr.infosgroup.security.jwt.auth.custom.liferay;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import cr.infosgroup.integraciones.liferay.ILiferay;
import cr.infosgroup.security.jwt.auth.AuthProvider;

@Component("authenticationProviderLiferay")
public class AuthenticationProviderLiferay implements AuthenticationProvider {
	
	private static final Logger log = LogManager.getLogger(AuthenticationProviderLiferay.class);
	
	/**
	 * Interface con liferay
	 */
	@Autowired
	private ILiferay liferay;
	
	@Autowired
	private UserDetailsService userDetailsServiceLiferay;
		
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		//Toma el objeto principal que contiene el usuario y el codigo de estacion
		//Principal principal = (Principal)authentication.getPrincipal();
		//String userName = principal.getUsername();
		
		String userName = (String)authentication.getPrincipal();
		String password = authentication.getCredentials() == null ? null : authentication.getCredentials().toString();
		
		if ( this.autenticacion(userName, password) ) {
			UserDetails userDetails = this.userDetailsServiceLiferay.loadUserByUsername(userName);
			UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
			user.setDetails(AuthProvider.LIFERAY.name());
			return user;
		}
		throw new BadCredentialsException("404");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	/**
	 * Autentica contra liferay 
	 * Retorna el userId si logro autenticar, "" en caso negativo
	 * @param name
	 * @param password
	 * @return
	 */
	private boolean autenticacion(String name, String password) {
	
		//Para la autenticacion se realizan varias validaciones en secuencia deben cumplirse
		//todas en forma secuencial
		
		//
		// 1) Se autentica contra Liferay
		//
		
		boolean autenticacionLiferay = this.liferay.autenticar(name, password);
		log.info("Autenticacion {} : {}", name, autenticacionLiferay);
		
		return autenticacionLiferay;
	}
}