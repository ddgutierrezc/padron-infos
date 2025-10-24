/**
 * Derechos reservados
 * Desarrollado por Infosgroup Costa Rica -
 * https://www.infosgroup.cr
 * 2025
 * @author lsanabria
 */
package cr.infosgroup.padron.bl.exception;

/**
 * Enun que define los tipos de error manejados a nivel de BL
 * 
 * @author usuario
 *
 */
public enum ErrorBL {

//ENUN
	
	//
	//
	//
	// AGREGAR ENUN PARA CADA ADMINISTRADOR QUE SE DEFINA CON EL SIGUIENTE FORMATO
	// ErrorBL_AdmXXXXX_1("AdmXXXXX_1","Mensaje tecnico AdmXXXXX_1","Mensaje usuario AdmXXXXX_1"),
	// ErrorBL_AdmXXXXX_2("AdmXXXXX_2","Mensaje tecnico AdmXXXXX_2","Mensaje usuario AdmXXXXX_2"),
	// ErrorBL_AdmXXXXX_N("AdmXXXXX_N","Mensaje tecnico AdmXXXXX_N","Mensaje usuario AdmXXXXX_N"),
	//
	//
	//
		
	//ErrorBL_AdmCiudadano_0("AdmCiudadano_0","Ciudadano no encontrado"),
	
	
	
	//Genericos
	ErrorBL_Gen_0("Gen_0","Gen_0_Tecnico"),
	ErrorBL_Gen_1("Gen_1","ConstraintViolationException"),
	
	ErrorBL_Gen_Aud("Gen_Aud","No se indicaron datos de auditoria"),
	
	ErrorBL_Gen_DTO("Gen_DTO","No se indica DTO"),
	
	ErrorBL_Gen_Adm_0("Gen_Adm-0","No se indica entidad"),
	
	ErrorBL_Gen_Ent_0("ErrorBL_Gen_Ent_0","Entidad no encontrada"),
	
	ErrorBL_Gen_BD_0("ErrorBL_Gen_BD_0","Error en operacion de base datos"),
	;	
	
//ATRIBUTOS DEL ENUM
	
	/**
	 * Codigo de la excepcion 
	 */
	private String blCode;
	
	/**
	 * Mensaje tecnico de la excepcion
	 */
	private String blTechMessage;
	
//METODOS DEL ENUM
	
	//Constructor
		
	private ErrorBL(String blCode,String blTechMessage) {
		this.blCode = blCode;
		this.blTechMessage = blTechMessage;
	}
	
	//publicos

	/**
	 * Sobrescribe metodo toString para facilitar la presentacion
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\tblCode: ");
		str.append(blCode);
		str.append("\n");
		str.append("\tTechMessage: ");
		str.append(blTechMessage);
		str.append("\n");
		return str.toString();
	}

	//protegidos
	
	//privados
	
	//set y get
	
	public String getBlCode() {
		return blCode;
	}

	public String getBlTechMessage() {
		return blTechMessage;
	}
}
