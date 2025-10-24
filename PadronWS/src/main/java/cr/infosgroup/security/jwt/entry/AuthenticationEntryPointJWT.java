package cr.infosgroup.security.jwt.entry;

import java.io.IOException;
import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//TODO: MEJORAR

/**
 * @author lsanabria
 */
public class AuthenticationEntryPointJWT implements AuthenticationEntryPoint {
	
	private static final Logger log = LogManager.getLogger(AuthenticationEntryPointJWT.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,AuthenticationException authException) throws IOException, ServletException {
		String ip = request.getRemoteAddr();
		String uri = request.getRequestURI();
		String error = authException.getMessage();
		String fecha = Instant.now().toString();
		log.warn("Acceso no autorizado: IP={} Fecha={} URI={} Error={}",ip,fecha,uri,error);
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		String respuesta = String.format("{\"error\":\"Unauthorized\",\"message\":\"%s\",\"path\":\"%s\",\"timestamp\":\"%s\"}",error,uri,fecha);
		response.getWriter().write(respuesta);
	}	
	
}
