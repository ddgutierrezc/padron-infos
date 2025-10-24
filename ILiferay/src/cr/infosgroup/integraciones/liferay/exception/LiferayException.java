/**
 * Derechos reservados
 * Desarrollado por Infosgroup Costa Rica -
 * https://www.infosgroup.cr
 * 2024
 */
package cr.infosgroup.integraciones.liferay.exception;

/**
 * Clase para el manejo de excepciones en BL
 * 
 * @author usuario
 *
 */
public class LiferayException  extends Exception {

//ATRIBUTOS DE CLASE
	
	/**
	 * serial
	 */
	private static final long serialVersionUID = 7522647677579553613L;

	/**
	 * Error de BL
	 */
	private ErrorBL errorBL;
	
//METODOS DE CLASE
	
	//Constructores
	
	/**
	 * Crea una BLExcepcion con ErrorBL
	 * 
	 * @param errorBL ErrorBL
	 */
	public LiferayException(ErrorBL errorBL) {
		super();
		this.errorBL = errorBL;
	}
	
	public LiferayException(ErrorBL errorBL, String customMessage) {
		super(customMessage);
		this.errorBL = errorBL;
	}
		
	/**
	 * Crea una BLExcepcion con ErrorBL y Throwable
	 * 
	 * @param errorBL ErrorBL
	 * @param throwable Throwable
	 */
	public LiferayException(ErrorBL errorBL,Throwable throwable) {
		super(throwable);
		this.errorBL = errorBL;
	}
	
	//publicos
	
	/**
	 * Sobre escribe metodo toString para facilitar mostrar el contenido de la excepcion 
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("errorBL: ");
		str.append(errorBL);
		str.append(" throwable: ");
		str.append(super.toString());
		return str.toString();
	}
	
	//protegidos
	
	//privados
	
	//set y get
	public ErrorBL getErrorBL() {
		return errorBL;
	}
}
