package cr.infosgroup.security.jwt.auth.custom.liferay;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import cr.infosgroup.integraciones.liferay.ILiferay;

@Component("userDetailsServiceLiferay")
public class UserDetailsServiceLiferay implements UserDetailsService {

	/**
	 * Interface con liferay
	 */
	@Autowired
	private ILiferay liferay;	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails user = this.findUser(username);
		
		return user;
	}
	
	private UserDetails findUser(String username) throws UsernameNotFoundException {
		//Consulta los roles
		List<String> roles = this.liferay.findUserRolesLiferay(username);
		
		//Crea listado de GrantedAuthority
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		//Recorre los roles creando GrantedAuthority
		GrantedAuthority grantedAuthority;
		for(String rol : roles){
			grantedAuthority = new SimpleGrantedAuthority(rol);
			authorities.add(grantedAuthority);
		}
		
		String password = "";
		boolean enabled = true;
		boolean accountNonExpired = true; 
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		UserDetails user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return user;
	}
}
