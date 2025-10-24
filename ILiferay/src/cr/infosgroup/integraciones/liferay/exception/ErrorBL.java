/**
 * Derechos reservados
 * Desarrollado por Infosgroup Costa Rica -
 * https://www.infosgroup.cr
 * 2025
 */
package cr.infosgroup.integraciones.liferay.exception;

/**
 * Enum que define los tipos de error manejados a nivel de BL para el sistema de gestión de programas de radio
 *
 * @author crodriguez
 *
 */
public enum ErrorBL {

	// ERRORES DE COMUNICACIÓN CON LIFERAY
	ErrorLiferay_WSLiferay_HTTP("ErrorLiferay_WSLiferay_HTTP",
			"Error de comunicación HTTP con el servidor Liferay",
			"Error de comunicación con el sistema. Verifique la conectividad."),

	ErrorLiferay_WSLiferay_General("ErrorLiferay_WSLiferay_General",
			"Error general en la operación con Liferay",
			"Error en la operación solicitada. Contacte al administrador."),

	// ERRORES DE VALIDACIÓN DE DATOS
	ErrorLiferay_WSLiferay_DatosInvalidos("ErrorLiferay_WSLiferay_DatosInvalidos",
			"Los datos proporcionados no son válidos para la operación",
			"Los datos proporcionados no son válidos. Verifique la información."),

	ErrorLiferay_WSLiferay_ParametroRequerido("ErrorLiferay_WSLiferay_ParametroRequerido",
			"Falta un parámetro requerido para completar la operación",
			"Falta información requerida para completar la operación."),

	// ERRORES ESPECÍFICOS DE RADIOS
	ErrorLiferay_WSLiferay_RadioNoEncontrada("ErrorLiferay_WSLiferay_RadioNoEncontrada",
			"La estación de radio especificada no fue encontrada",
			"La radio especificada no existe o no está disponible."),

	ErrorLiferay_WSLiferay_RadioNoEliminable("ErrorLiferay_WSLiferay_RadioNoEliminable",
			"La radio no puede ser eliminada debido a restricciones o contiene programas",
			"La radio no puede ser eliminada en este momento."),

	ErrorLiferay_WSLiferay_RadioDuplicada("ErrorLiferay_WSLiferay_RadioDuplicada",
			"Ya existe una radio con el nombre especificado",
			"Ya existe una radio con ese nombre. Elija otro nombre."),

	ErrorLiferay_WSLiferay_CarpetaProgramasNoCreada("ErrorLiferay_WSLiferay_CarpetaProgramasNoCreada",
			"No se pudo crear o encontrar la carpeta 'Programas' en la radio",
			"Error al configurar la carpeta de programas de la radio."),

	// ERRORES ESPECÍFICOS DE PROGRAMAS
	ErrorLiferay_WSLiferay_ProgramaNoEncontrado("ErrorLiferay_WSLiferay_ProgramaNoEncontrado",
			"El programa de radio especificado no fue encontrado",
			"El programa especificado no existe o no está disponible."),

	ErrorLiferay_WSLiferay_ProgramaNoEliminable("ErrorLiferay_WSLiferay_ProgramaNoEliminable",
			"El programa no puede ser eliminado debido a restricciones",
			"El programa no puede ser eliminado en este momento."),

	ErrorLiferay_WSLiferay_ProgramaSinRadio("ErrorLiferay_WSLiferay_ProgramaSinRadio",
			"No se especificó la radio a la que pertenece el programa",
			"Debe especificar la radio del programa."),

	// ERRORES ESPECÍFICOS DE TEMPORADAS
	ErrorLiferay_WSLiferay_TemporadaNoEncontrada("ErrorLiferay_WSLiferay_TemporadaNoEncontrada",
			"La temporada especificada no fue encontrada",
			"La temporada especificada no existe o no está disponible."),

	ErrorLiferay_WSLiferay_TemporadaSinEpisodios("ErrorLiferay_WSLiferay_TemporadaSinEpisodios",
			"La temporada debe tener al menos un episodio (restricción de Liferay)",
			"Una temporada debe tener al menos un episodio."),

	// ERRORES ESPECÍFICOS DE EPISODIOS
	ErrorLiferay_WSLiferay_EpisodioInvalido("ErrorLiferay_WSLiferay_EpisodioInvalido",
			"Los datos del episodio no son válidos",
			"La información del episodio no es válida."),

	ErrorLiferay_WSLiferay_EpisodioNoEliminable("ErrorLiferay_WSLiferay_EpisodioNoEliminable",
			"No se puede eliminar el último episodio de una temporada",
			"No se puede eliminar el último episodio de una temporada."),

	// ERRORES DE AUTENTICACIÓN Y AUTORIZACIÓN
	ErrorLiferay_WSLiferay_Autenticacion("ErrorLiferay_WSLiferay_Autenticacion",
			"Error de autenticación con el servidor Liferay",
			"Error de autenticación. Verifique las credenciales."),

	ErrorLiferay_WSLiferay_Autorizacion("ErrorLiferay_WSLiferay_Autorizacion",
			"No tiene permisos suficientes para realizar esta operación",
			"No tiene permisos para realizar esta operación."),

	// ERRORES DE SERIALIZACIÓN/DESERIALIZACIÓN
	ErrorLiferay_WSLiferay_JSON("ErrorLiferay_WSLiferay_JSON",
			"Error al procesar los datos JSON de la respuesta",
			"Error procesando la respuesta del servidor."),

	ErrorLiferay_WSLiferay_FormatoRespuesta("ErrorLiferay_WSLiferay_FormatoRespuesta",
			"El formato de la respuesta del servidor no es el esperado",
			"Formato de respuesta inesperado del servidor."),

	// ERRORES ESPECÍFICOS DE TAXONOMÍAS
	ErrorLiferay_WSLiferay_TaxonomiaNoEncontrada("ErrorLiferay_WSLiferay_TaxonomiaNoEncontrada",
			"El vocabulario o categoría de taxonomía especificada no fue encontrada",
			"La taxonomía especificada no existe o no está disponible."),

	ErrorLiferay_WSLiferay_TaxonomiaNoCreada("ErrorLiferay_WSLiferay_TaxonomiaNoCreada",
			"No se pudo crear el vocabulario o categoría de taxonomía",
			"Error al crear la categorización automática."),

	ErrorLiferay_WSLiferay_TaxonomiaNoEliminable("ErrorLiferay_WSLiferay_TaxonomiaNoEliminable",
			"La taxonomía no puede ser eliminada debido a restricciones o uso activo",
			"La categoría no puede ser eliminada en este momento."),

	ErrorLiferay_WSLiferay_RollbackFallido("ErrorLiferay_WSLiferay_RollbackFallido",
			"Falló la operación de rollback de cambios en taxonomías",
			"Error al deshacer los cambios realizados. Contacte al administrador.");

	
	//ATRIBUTOS DEL ENUM
	
	/**
	 * Codigo de la excepcion 
	 */
	private String blCode;
	
	/**
	 * Mensaje tecnico de la excepcion
	 */
	private String blTechMessage;
	
	/**
	 * Mensaje de usuario de la excepcion
	 */
	private String blUserMessage;	
	
//METODOS DEL ENUM
	
	//Constructor
		
	private ErrorBL(String blCode,String blTechMessage,String blUserMessage) {
		this.blCode = blCode;
		this.blTechMessage = blTechMessage;
		this.blUserMessage = blUserMessage;	
	}
	
	//publicos

	/**
	 * Sobrescribe metodo toString para facilitar la presentacion
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("blCode: ");
		str.append(blCode);
		str.append(" blTechMessage: ");
		str.append(blTechMessage);
		str.append(" blUserMessage: ");
		str.append(blUserMessage);		
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

	public String getBlUserMessage() {
		return blUserMessage;
	}

	public void setBlUserMessage(String blUserMessage) {
		this.blUserMessage = blUserMessage;
	}
}
