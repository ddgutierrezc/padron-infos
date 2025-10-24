package cr.infosgroup.security.jwt.auth.custom.prueba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import cr.infosgroup.security.jwt.auth.AuthProvider;

@Component("authenticationProviderPrueba")
public class AuthenticationProviderPrueba implements AuthenticationProvider {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsServicePrueba userDetailsServicePrueba;	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String name = authentication.getName();
		String password = authentication.getCredentials() == null ? null : authentication.getCredentials().toString();
		
		if (this.autenticacion(name, password)) {
			
			UserDetails userDetails = this.userDetailsServicePrueba.loadUserByUsername(name);
			UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
			user.setDetails(AuthProvider.PRUEBA.name());
			return user;
		} else {
			return null;
		}
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private boolean autenticacion(String name, String password) {
		String passwordCodificado = "";
		switch(name) {
			case "administrador":
				passwordCodificado = "{bcrypt}$2a$10$MhGIME0V7bq10unuMhnGs.xIjy3q91Z4tUuvWVESXsfo0Fam5v2wC";
				break;
			case "usuario":
				passwordCodificado = "{bcrypt}$2a$10$mGxG0gP11xpQzkBP3L5xEeSDtSrMiP55TGGZG.VS37aSF1qcp/eAu";
				break;
			case "invitado":
				passwordCodificado = "{bcrypt}$2a$10$TCDvP8PHXvYo4jzaGu6J4Ob74ASnh0rtsnfffcwsK6fARovrgJjo2";
				break;
			default:
				passwordCodificado = "";
				return false;
		}
		boolean respuesta = (password != null && this.passwordEncoder.matches(password,passwordCodificado));
		return respuesta;
	}
}
