package cr.infosgroup.security.jwt.model;

import java.io.Serializable;

/**
 * 
 * @author lsanabria
 *
 */
public class CambioClaveRequest implements Serializable {	

	
	private static final long serialVersionUID = 2056593232517462402L;
	
	private String username;
	private String password;
	private String nuevoPassword;
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("username: ");
		str.append(this.username);
		str.append(" password: ");
		str.append((this.password == null || this.password.isEmpty()) ? "no" : "si");
		str.append(" nuevoPassword: ");
		str.append((this.nuevoPassword == null || this.nuevoPassword.isEmpty()) ? "no" : "si");
		return str.toString();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNuevoPassword() {
		return nuevoPassword;
	}

	public void setNuevoPassword(String nuevoPassword) {
		this.nuevoPassword = nuevoPassword;
	}
}
