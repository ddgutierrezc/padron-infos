package cr.infosgroup.security.jwt.auth.custom.prueba;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsServicePrueba")
public class UserDetailsServicePrueba implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = this.findUser(username);
		return user;
	}
	
	private UserDetails findUser(String username) throws UsernameNotFoundException {
		String password;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		switch(username) {
		case "administrador":
			password = "{bcrypt}$2a$10$MhGIME0V7bq10unuMhnGs.xIjy3q91Z4tUuvWVESXsfo0Fam5v2wC";
			authorities.add(new SimpleGrantedAuthority("rolAdministrador"));
			break;
		case "usuario":
			password = "{bcrypt}$2a$10$mGxG0gP11xpQzkBP3L5xEeSDtSrMiP55TGGZG.VS37aSF1qcp/eAu";
			authorities.add(new SimpleGrantedAuthority("rolUsuario"));
			break;
		case "invitado":
			password = "{bcrypt}$2a$10$TCDvP8PHXvYo4jzaGu6J4Ob74ASnh0rtsnfffcwsK6fARovrgJjo2";
			authorities.add(new SimpleGrantedAuthority("rolInvitado"));
			break;
		default:
			throw new UsernameNotFoundException("No se encontro el usuario: " + username);
		}
		
		boolean enabled = true;
		boolean accountNonExpired = true; 
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		UserDetails user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return user;
	}
}
