package cr.infosgroup.security.jwt;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;

import cr.infosgroup.security.jwt.exception.SecurityException;
import jakarta.servlet.http.HttpServletRequest;

public interface ISeguridadJWT {
	
	public String autenticar(AuthenticationManager authenticationManager,String userName,String password,String canal,String ip) throws Exception;
	public String getTokenFromRequest(HttpServletRequest request);
	public Boolean isValidToken(String token, String id) throws SecurityException;
	//Claims standard
	public String getClaimId(String token) throws SecurityException;
	public String getClaimIssuer(String token) throws SecurityException;
	public Date getClaimIssuedAt(String token) throws SecurityException;
	public Date getClaimNotBefore(String token) throws SecurityException;
	public Date getClaimExpiration(String token) throws SecurityException;
	public String getClaimSubject(String token) throws SecurityException;
	//Claims especificos
	public String getClaimIp(String token) throws SecurityException;
	public String getClaimCanal(String token) throws SecurityException;
	public String getClaimAuthProvider(String token) throws SecurityException;
	public String getClaimRolesUsuario(String token) throws SecurityException;
	//Agregar aca los que ocupe la aplicacion
	public String getClaimXXXXX(String token) throws SecurityException;
}
