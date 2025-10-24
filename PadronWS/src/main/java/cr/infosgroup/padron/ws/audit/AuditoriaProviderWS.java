package cr.infosgroup.padron.ws.audit;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cr.infosgroup.padron.dal.audit.IAuditoriaProvider;

/**
 * Proveedor de auditoria cuando se ejecutan operaciones desde WS usando el contexto de seguridad
 *
 * @author lsanabria
 */
@Component("auditoriaProviderWS")
public class AuditoriaProviderWS implements IAuditoriaProvider {

    @Override
    public String getUsuarioAuditoria() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No hay usuario autenticado en el contexto de seguridad.");
        }
        return authentication.getName();
    }
}
