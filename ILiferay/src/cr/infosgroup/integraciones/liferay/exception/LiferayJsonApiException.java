package cr.infosgroup.integraciones.liferay.exception;

import java.net.HttpURLConnection;

import cr.infosgroup.comun.json.JsonUtil;
import cr.infosgroup.integraciones.liferay.jsonws.exception.JsonWsException;

public class LiferayJsonApiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7909663353752081005L;

	private int statusCode;

	private String exception = "";
	
	private String message = "";

	public LiferayJsonApiException() {
		this.statusCode = HttpURLConnection.HTTP_INTERNAL_ERROR;;
	}	
	
	public LiferayJsonApiException(int statusCode) {
		this.statusCode = statusCode;
	}
	
	private static String SecurityException = "java.lang.SecurityException"; 
	private static String AuthenticatedAccessRequired = "Authenticated access required";
	
	public LiferayJsonApiException(String jsonResponse){
		//Intenta convertir en JsonWsException 
		try {
			JsonWsException jsonWsException = JsonUtil.getObject(jsonResponse, JsonWsException.class);
			
			if(SecurityException.equalsIgnoreCase(jsonWsException.getException()) && AuthenticatedAccessRequired.equalsIgnoreCase(jsonWsException.getMessage())) {
				this.statusCode = HttpURLConnection.HTTP_UNAUTHORIZED;
				this.exception = jsonWsException.getException();
				this.message = jsonWsException.getMessage();
			}
			//AGREGAR ACA LAS EXCEPCIONES CONOCIDAS
			//else if() {}
			else {
				//EXCEPCION NO MANEJADA LA REPORTA COMO ERROR INTERNO
				this.statusCode = HttpURLConnection.HTTP_INTERNAL_ERROR;
				this.exception = jsonWsException.getException();
				this.message = jsonWsException.getMessage();
				
			}	
		}catch(Exception e) {
			//No pudo convertir JsonWsException
			this.statusCode = HttpURLConnection.HTTP_INTERNAL_ERROR;
		}
	}	
	
	public int getStatusCode() {
		return statusCode;
	}

	public String getException() {
		return exception;
	}

	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(" statusCode: ");
		stringBuilder.append(this.statusCode);
		stringBuilder.append(" exception: ");
		stringBuilder.append(this.getException());
		stringBuilder.append(" message: ");
		stringBuilder.append(this.getMessage());
		return stringBuilder.toString();
	}
}
