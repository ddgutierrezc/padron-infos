package cr.infosgroup.security.util;

import jakarta.servlet.http.HttpServletRequest;

public class Utileria {
	
	public static String getRemoteIP(HttpServletRequest httpServletRequest){
		String ipCliente = "";
		if(httpServletRequest!=null) {
			ipCliente = httpServletRequest.getHeader("x-forwarded-for");
			if(ipCliente == null  || ipCliente.isBlank()) {
				ipCliente = httpServletRequest.getRemoteAddr();
			}			
		}
		return ipCliente;
	}

}
