package cr.infosgroup.padron.bl.adm;

import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.UnexpectedRollbackException;

import cr.infosgroup.padron.bl.exception.BLException;
import cr.infosgroup.padron.bl.exception.ErrorBL;
import cr.infosgroup.padron.bl.exception.ThrowingRunnable;
import cr.infosgroup.padron.dal.finder.FinderCiudadano;
import cr.infosgroup.padron.dal.finder.FinderDireccionElectoral;

public class BaseAdm {
	
    @Autowired    
    private FinderCiudadano finderCiudadano;
    
    @Autowired    
    private FinderDireccionElectoral finderDireccionElectoral;

	protected <T> T ejecutarConManejoDeErroresBL(Class<?> origen, Callable<T> operacion) throws BLException {
		Logger log = LogManager.getLogger(origen);
		try {
			return operacion.call();
		} 
		catch (BLException ble) {
			log.error("Error de negocio en " + origen.getSimpleName(), ble);
			throw ble;
		}
		catch (ConstraintViolationException cve) {
			log.error("Error en operacion de base datos en " + origen.getSimpleName(), cve);
			throw new BLException(ErrorBL.ErrorBL_Gen_BD_0, cve);
		}
		catch (UnexpectedRollbackException ure) {
			log.error("Error en operacion de base datos en " + origen.getSimpleName(), ure);
			throw new BLException(ErrorBL.ErrorBL_Gen_BD_0, ure);
		}
		catch (Exception e) {
			log.error("Error inesperado en " + origen.getSimpleName(), e);
			throw new BLException(ErrorBL.ErrorBL_Gen_0, e);
		}
	}
	
	protected void ejecutarSinResultadoConManejoDeErroresBL(Class<?> origen, ThrowingRunnable operacion) throws BLException {
		Logger log = LogManager.getLogger(origen);
		try {
			operacion.run();
		} 
		catch (BLException ble) {
			log.error("Error de negocio en " + origen.getSimpleName(), ble);
			throw ble;
		} 
		catch (ConstraintViolationException cve) {
			log.error("Error en operacion de base datos en " + origen.getSimpleName(), cve);
			throw new BLException(ErrorBL.ErrorBL_Gen_BD_0, cve);
		}
		catch (UnexpectedRollbackException ure) {
			log.error("Error en operacion de base datos en " + origen.getSimpleName(), ure);
			throw new BLException(ErrorBL.ErrorBL_Gen_BD_0, ure);
		}
		catch (Exception e) {
			log.error("Error inesperado en " + origen.getSimpleName(), e);
			throw new BLException(ErrorBL.ErrorBL_Gen_0, e);
		}
	}
    
	public FinderCiudadano getFinderCiudadano() {
		return finderCiudadano;
	}

	public void setFinderCiudadano(FinderCiudadano finderCiudadano) {
		this.finderCiudadano = finderCiudadano;
	}

	public FinderDireccionElectoral getFinderDireccionElectoral() {
		return finderDireccionElectoral;
	}

	public void setFinderDireccionElectoral(FinderDireccionElectoral finderDireccionElectoral) {
		this.finderDireccionElectoral = finderDireccionElectoral;
	}
}
