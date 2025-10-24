package cr.infosgroup.padron.dal.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * Implementacion de AuditorAware
 * 
 * @author lsanabria
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    private final IAuditoriaProvider auditoriaProvider;

    public AuditorAwareImpl(IAuditoriaProvider auditoriaProvider) {
        this.auditoriaProvider = auditoriaProvider;
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(this.auditoriaProvider.getUsuarioAuditoria());
    }
}
