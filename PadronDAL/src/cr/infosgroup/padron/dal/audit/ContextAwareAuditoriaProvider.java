package cr.infosgroup.padron.dal.audit;

import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Contexto de proveedores de auditoria
 * 
 * @author lsanabria
 */
public class ContextAwareAuditoriaProvider implements IAuditoriaProvider {

	/**
	 * Provider de auditoria de WS
	 */
    private final IAuditoriaProvider auditoriaProviderWS;
	/**
	 * Provider de auditoria de BL
	 */
    private final IAuditoriaProvider auditoriaProviderBL;

    /**
     * Contexto utilizado cuando se corre desde WS
     * @param auditoriaProviderWS
     * @param auditoriaProviderBL
     * 
     * @author lsanabria
     */
    public ContextAwareAuditoriaProvider(
            @Qualifier("auditoriaProviderWS") IAuditoriaProvider auditoriaProviderWS,
            @Qualifier("auditoriaProviderBL") IAuditoriaProvider auditoriaProviderBL
        ) {
            this.auditoriaProviderWS = auditoriaProviderWS;
            this.auditoriaProviderBL = auditoriaProviderBL;
    }

    /**
     * Contexto utilizado cuando se corre desde BL
     * @param auditoriaProviderBL
     * 
     * @author lsanabria
     */
    public ContextAwareAuditoriaProvider(
        @Qualifier("auditoriaProviderBL") IAuditoriaProvider auditoriaProviderBL
    ) {
    	this.auditoriaProviderWS = null;
        this.auditoriaProviderBL = auditoriaProviderBL;
    }

    /**
     * Retorna el usuario de autitoria segun el provider que corresponda
     */
    @Override
    public String getUsuarioAuditoria() {
        try {
        	String usuarioAuditoria;
        	if(this.auditoriaProviderWS != null) {
        		usuarioAuditoria = this.auditoriaProviderWS.getUsuarioAuditoria();	
        	}
        	else {
        		usuarioAuditoria = this.auditoriaProviderBL.getUsuarioAuditoria();	
        	}
        	return usuarioAuditoria;
        } 
        catch (IllegalStateException e) {
        	throw e;
        }
    }
}
