package cr.infosgroup.padron.ws.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.UnexpectedRollbackException;

import cr.infosgroup.padron.bl.adm.interfaces.IAdmCiudadano;
import cr.infosgroup.padron.bl.adm.interfaces.IAdmDireccionElectoral;
import cr.infosgroup.padron.bl.exception.BLException;
import cr.infosgroup.padron.bl.exception.ErrorBL;
import cr.infosgroup.padron.ws.rest.model.BaseResponse;

/**
 * Clase base de los controladores de servicios rest
 * 
 * @author lsanabria@infosgroup.cr
 * 
 */
public class BaseCtl {

	private static final String ERROR_ACCESS_DENIED = "accessDeniedException";
    private static final String ERROR_GENERIC = "exception";
	
	//Valores de configuracion importados del archivo project.properties
	
	//ATRIBUTOS DE CLASE
	@Value("${project.apiRoot}")
	public static final String API_ROOT = "/api/padron";
				
	@Value("${project.jwt.tokenHeader}")
	private String tokenHeader;

	//Administradores
	
	@Autowired
	private IAdmCiudadano admCiudadano;
	
	@Autowired
	private IAdmDireccionElectoral admDireccionElectoral;

	//Administradores

	//@Autowired
	//private IAdmXXX admXXX;

    // Manejo centralizado de excepciones
	protected <T extends BaseResponse> ResponseEntity<T> manejarExcepcionWS(Class<?> origen, T respuesta, Exception e) {
		Logger log = LogManager.getLogger(origen);
		log.error("Error en {}", origen.getSimpleName(), e);
		respuesta.setSuccess(false);
		
		if (e instanceof BLException blEx) {
			ErrorBL errorBL = blEx.getErrorBL();
			respuesta.addError(errorBL.getBlCode(), errorBL.getBlTechMessage());
			return ResponseEntity.ok(respuesta);
		}

		if (e instanceof AccessDeniedException) {
			respuesta.addError(ERROR_ACCESS_DENIED, "Acceso denegado");
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(respuesta);
		}

		if (e instanceof UnexpectedRollbackException urex) {
			BLException blException  = new BLException(ErrorBL.ErrorBL_Gen_BD_0, urex);
			respuesta.addError(blException.getErrorBL().getBlCode(), blException.getErrorBL().getBlTechMessage());
			return ResponseEntity.ok(respuesta);
		}		
		
		respuesta.addError(ERROR_GENERIC, "Error interno del servidor");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
	}
      
	// SET Y GET	
	public IAdmCiudadano getAdmCiudadano() {
		return admCiudadano;
	}

	public IAdmDireccionElectoral getAdmDireccionElectoral() {
		return admDireccionElectoral;
	}
}