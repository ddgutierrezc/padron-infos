package cr.infosgroup.padron.ws.util;

import java.util.Enumeration;

import jakarta.servlet.http.HttpServletRequest;

public class HttpUtil {
	
	public static String getRequestInfo(HttpServletRequest request) {
		StringBuilder stringBuilder = new StringBuilder();		
		stringBuilder.append("\n--------------------------------------------------------------------------\n");
		stringBuilder.append("Request path: " + request.getContextPath() + request.getServletPath() + "\n");
		stringBuilder.append("Method: " + request.getMethod() + " ");
		stringBuilder.append("RemoteHost: " + request.getRemoteHost() + " ");
		stringBuilder.append("RemoteAddr: " + request.getRemoteAddr() + " ");
		stringBuilder.append("RemotePort: " + request.getRemotePort() + " ");
		stringBuilder.append("RemoteUser: " + request.getRemoteUser() + " ");
		stringBuilder.append("Ip Addr: " + request.getHeader("x-forwarded-for") + "\n");		
		stringBuilder.append("QueryString: " + request.getQueryString() + "\n");
	
//		//HeaderNames
//		stringBuilder.append("--Headers--\n");
//		Enumeration<String> headerNames = request.getHeaderNames();
//		String header = "";
//		while(headerNames.hasMoreElements()) {
//			header = (String)headerNames.nextElement();
//			stringBuilder.append(header + ": " + request.getHeader(header) + "\n");
//		}
//
//		//AttributesNames
//		stringBuilder.append("--Atrributes--\n");
//		Enumeration<String> attributeNames = request.getAttributeNames();
//		String attribute = "";
//		while(attributeNames.hasMoreElements()) {
//			attribute = (String)attributeNames.nextElement();
//			stringBuilder.append(attribute + ": " + request.getHeader(attribute) + "\n");
//		}
//		
//	    // Parámetros
//	    stringBuilder.append("--Parameters--\n");
//	    Enumeration<String> parameterNames = request.getParameterNames();
//	    while (parameterNames.hasMoreElements()) {
//	        String paramName = parameterNames.nextElement();
//	        String[] paramValues = request.getParameterValues(paramName);
//	        for (String value : paramValues) {
//	            stringBuilder.append(paramName + ": " + value + "\n");
//	        }
//	    }
	    
		stringBuilder.append("--------------------------------------------------------------------------\n");		
		return stringBuilder.toString();
	}

	public static String printHeaderNames(HttpServletRequest request) {
        StringBuilder str = new StringBuilder();
        //HEADERS
        Enumeration<String> headerNames = request.getHeaderNames();
        String key = null;
        String value = null;
        while (headerNames.hasMoreElements()) {
            key = headerNames.nextElement();
            value = request.getHeader(key);
            str.append("Key: ");
            str.append(key);
            str.append(" Value: ");
            str.append(value);
            str.append("\n");
        }
        return str.toString();
    }	
	
	public static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
        	remoteAddr = request.getHeader("x-forwarded-for");
        	//remoteAddr = request.getHeader("X-REAL-IP");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }	
	
}
