package cr.infosgroup.security.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cr.infosgroup.comun.json.JsonUtil;
import cr.infosgroup.security.jwt.auth.AuthProvider;
import cr.infosgroup.security.jwt.enums.Canales;
import cr.infosgroup.security.jwt.exception.SecurityException;
import cr.infosgroup.security.jwt.services.LoginAttemptService;
import cr.infosgroup.security.jwt.util.JsonWebTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@Component("seguridadJWT")
public class SeguridadJWT implements ISeguridadJWT {
	
    private static final Logger log = LogManager.getLogger(SeguridadJWT.class);
    
	//Claims standard
    private final String CLAIM_IP = "ip";
	private final String CLAIM_CANAL = "canal";
	private final String CLAIM_AUTH_PROVIDER = "authProvider";
	private final String CLAIM_ROLES_USUARIO = "rolesUsuario";
	//Claims especificos 
	private final String CLAIM_XXXXX = "xxxxx";
	
	@Value("${project.jwt.tokenHeader}")
	private String tokenHeader;
	
//	@Autowired
//	private JsonWebTokenUtil jsonWebTokenUtil;	
	
//	@Autowired
//	private AuthenticationManager authenticationManager;    
	
//	@Autowired
//	private LoginAttemptService loginAttemptService;  
	
	private final JsonWebTokenUtil jsonWebTokenUtil;	
	private final LoginAttemptService loginAttemptService; 
	
    public SeguridadJWT(JsonWebTokenUtil jsonWebTokenUtil, LoginAttemptService loginAttemptService) {
        this.jsonWebTokenUtil = jsonWebTokenUtil;
        this.loginAttemptService = loginAttemptService;
    }
	
    public String autenticar(AuthenticationManager authenticationManager,String userName,String password,String canal,String ip) throws Exception{
    	try {
			//Validar intentos fallidos
	        if (this.loginAttemptService.isBlocked(userName,ip)) {
	            log.warn("{}-{} BLOQUEADO para autenticar por maximo de intentos fallidos",userName,ip);
	            throw new Exception();
	        }
	        
	        //Validar canal
            this.validarCanal(canal);
    		
            //Realiza la autenticacion
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName,password);
			Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			String strAuthProvider = (String)authentication.getDetails();
	
			AuthProvider authProvider = AuthProvider.getAuthProvider(strAuthProvider);
			
			//Crea el contexto de seguridad
			SecurityContextHolder.getContext().setAuthentication(authentication);
            
			//Crea la lista de roles
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			List<String> roles = new ArrayList<String>();
			String rol;
			for (GrantedAuthority authority : authorities) {
				rol = authority.getAuthority();
				roles.add(rol);
			}
			String jsonRolesUsuario = JsonUtil.getJson(roles);
			
            String token = this.buildToken(userName, ip, canal, authProvider.name(), jsonRolesUsuario);
			
            this.loginAttemptService.loginSucceeded(userName,ip);
            return token;
    	}
    	catch(Exception e) {
    		System.out.println(e);
            this.loginAttemptService.loginFailed(userName,ip);
            throw e;
    	}
    }
    
    public Boolean isValidToken(String token, String id) throws SecurityException {
    	return this.jsonWebTokenUtil.isValidToken(token, id);
    }
    
    private void validarCanal(String canal) throws BadCredentialsException {
        boolean canalValido = false;
        for (Canales c : Canales.values()) {
            if (c.getNombre().equalsIgnoreCase(canal)) {
                canalValido = true;
                break;
            }
        }
        if (!canalValido) {
            throw new BadCredentialsException("CANAL NO VALIDO");
        }
    }
    
	private String buildToken(String username, String ip, String canal,String authProvider, String jsonRolesUsuario) throws SecurityException, Exception{
		Map<String,Object> claims = new HashMap<String, Object>();
		//Claims standard
		claims.put(Claims.ID, username);
		//Claims especificos 
		claims.put(CLAIM_IP, ip);
		claims.put(CLAIM_CANAL, canal);
		claims.put(CLAIM_AUTH_PROVIDER, authProvider);
		claims.put(CLAIM_ROLES_USUARIO, jsonRolesUsuario);
		//Agregar aca los que ocupe la aplicacion
		claims.put(CLAIM_XXXXX, "VALOR_XXXXX");

		//Genera 
		String token = this.jsonWebTokenUtil.getTokenFromClaims(claims);
		return token;
	}
	
	public String getTokenFromRequest(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		return token;
	}
	
	//Claims standard
	public String getClaimId(String token) throws SecurityException {
		return this.jsonWebTokenUtil.getId(token);
	}

	public String getClaimIssuer(String token) throws SecurityException {
		return this.jsonWebTokenUtil.getIssuer(token);
	}

	public Date getClaimIssuedAt(String token) throws SecurityException {
		return this.jsonWebTokenUtil.getIssuedAt(token);
	}

	public Date getClaimNotBefore(String token) throws SecurityException {
		return this.jsonWebTokenUtil.getNotBefore(token);
	}

	public Date getClaimExpiration(String token) throws SecurityException {
		return this.jsonWebTokenUtil.getExpiration(token);
	}

	public String getClaimSubject(String token) throws SecurityException {
		return this.jsonWebTokenUtil.getSubject(token);
	}
	
	//Claims especificos
	
	public String getClaimIp(String token) throws SecurityException{
		String valorClaim = this.jsonWebTokenUtil.getClaimEspecifico(token, CLAIM_IP, String.class);
		return valorClaim;
	}
	
	public String getClaimCanal(String token) throws SecurityException{
		String valorClaim = this.jsonWebTokenUtil.getClaimEspecifico(token, CLAIM_CANAL, String.class);
		return valorClaim;
	}

	public String getClaimAuthProvider(String token) throws SecurityException{
		String valorClaim = this.jsonWebTokenUtil.getClaimEspecifico(token, CLAIM_AUTH_PROVIDER, String.class);
		return valorClaim;
	}
	
	public String getClaimRolesUsuario(String token) throws SecurityException{
		String valorClaim = this.jsonWebTokenUtil.getClaimEspecifico(token, CLAIM_ROLES_USUARIO, String.class);
		return valorClaim;
	}
	
	//Agregar aca los que ocupe la aplicacion
	public String getClaimXXXXX(String token) throws SecurityException{
		String valorClaim = this.jsonWebTokenUtil.getClaimEspecifico(token, CLAIM_XXXXX, String.class);
		return valorClaim;
	}
}
