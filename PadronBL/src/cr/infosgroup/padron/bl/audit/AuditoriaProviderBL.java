package cr.infosgroup.padron.bl.audit;

import org.springframework.stereotype.Component;

import cr.infosgroup.padron.dal.audit.IAuditoriaProvider;

/**
 * Proveedor de auditoria cuando se ejecutan operaciones desde BL sin un contexto de seguridad
 * 
 *	AuditoriaProviderBL.setUsuarioAuditoria("sistema");
 *	//OPERACIONES
 *	AuditoriaProviderBL.clear();
 *
 * @author lsanabria
 */
@Component("auditoriaProviderBL")
public class AuditoriaProviderBL implements IAuditoriaProvider {

	/**
	 * Hilo actual
	 */
    private static final ThreadLocal<String> currentAuditor = new ThreadLocal<>();

    /**
     * Define usuario
     * @param usuarioAuditoria
     */
    public static void setUsuarioAuditoria(String usuarioAuditoria) {
        currentAuditor.set(usuarioAuditoria);
    }

    /**
     * Limpia el provider
     */
    public static void clear() {
        currentAuditor.remove();
    }

    /**
     * Retorna el usuario
     */
	@Override
	public String getUsuarioAuditoria() {
        String usuarioAuditoria = currentAuditor.get();
        if (usuarioAuditoria == null) {
            throw new IllegalStateException("No se ha definido usuario para auditoria en BL.");
        }
        return usuarioAuditoria;
	}
}