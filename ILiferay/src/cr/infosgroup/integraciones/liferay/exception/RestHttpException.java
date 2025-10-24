package cr.infosgroup.integraciones.liferay.exception;

/**
 * Excepción personalizada para errores HTTP.
 */
public class RestHttpException extends RuntimeException {
	
    private static final long serialVersionUID = 6995598491324855783L;
    
	private final int statusCode;

    public RestHttpException(int statusCode) {
        super("HTTP error: " + statusCode);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
