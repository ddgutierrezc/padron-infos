package cr.infosgroup.padron.ws.rest.ctl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.infosgroup.padron.ws.rest.BaseCtl;
import cr.infosgroup.padron.ws.rest.model.Response;
import cr.infosgroup.padron.ws.util.HttpUtil;
import cr.infosgroup.security.jwt.ISeguridadJWT;
import cr.infosgroup.security.jwt.model.AuthenticationRequest;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(BaseCtl.API_ROOT + "/security")
public class AuthCtl extends BaseCtl {

    private static final String ERROR_GENERIC = "exception";
	private static final String ERROR_BAD_CREDENTIALS = "badCredentialsException";
	
    private static final Logger log = LogManager.getLogger(AuthCtl.class);

    @Autowired
    private ISeguridadJWT seguridadJWT;
    
    @Autowired
    private String version;
    
	@Autowired
	private AuthenticationManager authenticationManager;    
   
    @GetMapping(value = "/about")
    public ResponseEntity<Response<String>> getAbout(HttpServletRequest request) {
        Response<String> respuesta = new Response<>();
        try {
            respuesta.setData(this.version);
            return ResponseEntity.ok(respuesta);
        } 
        catch (Exception ex) {
            log.error(ex);
            respuesta.setSuccess(false);
            respuesta.addError(ERROR_GENERIC, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<Response<String>> auth(
    											@RequestBody AuthenticationRequest authenticationRequest,
    											HttpServletRequest request) {
        Response<String> respuesta = new Response<>();
        try {
			//UserName
			String userName = authenticationRequest.getUserName();
			//Password
			String password = authenticationRequest.getPassword();
			//Canal
			String canal = authenticationRequest.getCanal();
			//Ip
			String ip = HttpUtil.getClientIp(request);
			
			String token = this.seguridadJWT.autenticar(this.authenticationManager,userName,password,canal,ip);
			respuesta.setData(token);
            return ResponseEntity.ok(respuesta);
        } 
        catch (BadCredentialsException ex) {
            log.error(ex);            
            respuesta.setSuccess(false);
            respuesta.addError(ERROR_BAD_CREDENTIALS, ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);

        } 
        catch (Exception ex) {
            log.error(ex);
            respuesta.setSuccess(false);
            respuesta.addError(ERROR_GENERIC, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @GetMapping("/refresh")
    public ResponseEntity<Response<String>> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        Response<String> respuesta = new Response<>();
        try {
            //String token = this.seguridadJWT.getTokenFromRequest(request);
            // TODO: refrescar token
            // String refreshedToken = super.refreshToken(token);
            // respuesta.setData(refreshedToken);
            return ResponseEntity.ok(respuesta);
        } catch (Exception ex) {
            log.error(ex);
            respuesta.setSuccess(false);
            respuesta.addError(ERROR_GENERIC, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }
}
