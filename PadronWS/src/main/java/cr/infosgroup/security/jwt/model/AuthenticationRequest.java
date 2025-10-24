package cr.infosgroup.security.jwt.model;

import java.io.Serializable;

/**
 * 
 * @author lsanabria
 *
 */
public class AuthenticationRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -915238366341446833L;
	
	private String userName;
	private String password;
	private String canal;

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("userName: ");
		str.append(this.userName);
		str.append(" password: ");
		str.append((this.password == null || this.password.isEmpty()) ? "no" : "si");
		str.append(" canal: ");
		str.append(this.canal);
		return str.toString();
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}
}
