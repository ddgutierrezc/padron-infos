/**
 * Derechos reservados
 * Desarrollado por Infosgroup Costa Rica -
 * https://www.infosgroup.cr
 * 2025
 * @author lsanabria
 */
package cr.infosgroup.padron.bl.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para el manejo de excepciones en BL
 * 
 * @author lsanabria
 *
 */
public class BLException  extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3870405352535866532L;

//ATRIBUTOS DE CLASE
	private List<String> parametros = new ArrayList<String>();
	
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
	public BLException(ErrorBL errorBL) {
		super();
		this.errorBL = errorBL;
	}
	
	/**
	 * Crea una BLExcepcion con ErrorBL y lista de parametros de error
	 * 
	 * @param errorBL ErrorBL
	 */
	public BLException(ErrorBL errorBL,List<String> parametros) {
		super();
		this.errorBL = errorBL;
		this.parametros = parametros;
	}
		
	/**
	 * Crea una BLExcepcion con ErrorBL y Throwable
	 * 
	 * @param errorBL ErrorBL
	 * @param throwable Throwable
	 */
	public BLException(ErrorBL errorBL,Throwable throwable) {
		super(throwable);
		this.errorBL = errorBL;
	}
	
	/**
	 * Crea una BLExcepcion con ErrorBL y Throwable
	 * 
	 * @param errorBL ErrorBL
	 * @param throwable Throwable
	 */
	public BLException(ErrorBL errorBL,Throwable throwable,List<String> parametros) {
		super(throwable);
		this.errorBL = errorBL;
		this.parametros = parametros;
	}
	
	//publicos
	
	/**
	 * Sobre escribe metodo toString para facilitar mostrar el contenido de la excepcion 
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("errorBL:\n");
		str.append(errorBL);
		str.append("throwable:\n\t");
		str.append(super.toString());
		str.append("\n");
		str.append("parametros:\n[");
		for(String p : this.parametros) {
			str.append(" ");
			str.append(p);
			str.append(" ");
		}
		str.append("]");
		return str.toString();
	}
	
	//protegidos
	
	//privados
	
	//set y get
	public ErrorBL getErrorBL() {
		return errorBL;
	}
}
