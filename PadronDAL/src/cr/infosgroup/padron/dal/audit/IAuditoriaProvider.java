package cr.infosgroup.padron.dal.audit;

/**
 * Interface que define comportamiento del proveedor de auditoria
 * @author lsanabria
 */
public interface IAuditoriaProvider {
	
	/**
	 * Retorna usuario que se registra en pistas de auditoria
	 * @return
	 * @author lsanabria
	 */
    public String getUsuarioAuditoria();

}
