package cr.infosgroup.security.jwt.auth;

public class Principal {
	
	private String username;
	
	public Principal(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}
