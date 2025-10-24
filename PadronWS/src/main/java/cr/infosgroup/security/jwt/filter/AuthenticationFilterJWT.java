package cr.infosgroup.security.jwt.filter;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import cr.infosgroup.padron.ws.util.HttpUtil;
import cr.infosgroup.security.jwt.ISeguridadJWT;
import cr.infosgroup.security.jwt.auth.AuthProvider;
import cr.infosgroup.security.jwt.auth.UserDetailsServices;
import cr.infosgroup.security.jwt.exception.ErrorSecurity;
import cr.infosgroup.security.jwt.exception.SecurityException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro para verificar el token enviado por el usuario
 * 
 * @author lsanabria
 */
public class AuthenticationFilterJWT extends OncePerRequestFilter {

	private static final Logger log = LogManager.getLogger(AuthenticationFilterJWT.class);

	private final ISeguridadJWT seguridadJWT;
	
	private final UserDetailsServices userDetailsServices;

	public AuthenticationFilterJWT(ISeguridadJWT seguridadJWT,UserDetailsServices userDetailsServices) {
		this.seguridadJWT = seguridadJWT;
		this.userDetailsServices = userDetailsServices;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {
			String token = seguridadJWT.getTokenFromRequest(request);

			if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				String userName = seguridadJWT.getClaimId(token);
				String claimCanal = seguridadJWT.getClaimCanal(token);
				String claimAuthProvider = seguridadJWT.getClaimAuthProvider(token);
				String ip = HttpUtil.getClientIp(request);
				String requestInfo = HttpUtil.getRequestInfo(request);

				log.info("ip: {} claimCanal: {} userName: {} request: {}", ip, claimCanal, userName, requestInfo);

				if (seguridadJWT.isValidToken(token, userName)) {
					
					//Recarga la informacion del usuario
					UserDetails userDetails = this.userDetailsServices.loadUserByUsername(userName, AuthProvider.getAuthProvider(claimAuthProvider));
					
					//Crea contexto de seguridad
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} 
				else {
					throw new SecurityException(ErrorSecurity.ErrorSecurity_MalformedJwtException);
				}
			}

		} 
		catch (SecurityException securityException) {
			log.error("Error de seguridad: {}", securityException.getMessage(), securityException);
			ErrorSecurity error = securityException.getErrorSecurity();
			int status = switch (error) {
			case ErrorSecurity_ExpiredJwtException -> HttpServletResponse.SC_PRECONDITION_FAILED;
			case ErrorSecurity_MalformedJwtException -> HttpServletResponse.SC_UNAUTHORIZED;
			default -> HttpServletResponse.SC_UNAUTHORIZED;
			};
			response.setStatus(status);
			return;
		} 
		catch (AuthenticationException authEx) {
			log.error("Error de autenticación: {}", authEx.getMessage(), authEx);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		} 
		catch (Exception ex) {
			log.error("Error inesperado en el filtro JWT: {}", ex.getMessage(), ex);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		chain.doFilter(request, response);
	}
}