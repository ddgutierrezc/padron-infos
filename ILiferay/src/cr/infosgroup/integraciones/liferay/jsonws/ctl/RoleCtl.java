package cr.infosgroup.integraciones.liferay.jsonws.ctl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;

import cr.infosgroup.integraciones.liferay.ctl.BaseCtl;
import cr.infosgroup.integraciones.liferay.exception.LiferayJsonApiException;
import cr.infosgroup.integraciones.liferay.jsonws.model.Role;

/**
 * Controlador para servicio role
 * 
 * @author lsanabria
 */
public class RoleCtl extends BaseCtl {

    private static final Logger log = LogManager.getLogger(RoleCtl.class);

    private static String SERVICIO = BaseCtl.ROOT_API_JSONWS + "role/";
    private static String URL_GET_USER_ROLES = SERVICIO + "get-user-roles/user-id/{userId}";
    private static String URL_GET_ROLES_BY_NAME = SERVICIO + "get-role/company-id/{companyID}/name/{name}";
        
    public RoleCtl(String urlRootLiferay, String userLiferay, String passwordLiferay) {
        super(urlRootLiferay, userLiferay, passwordLiferay);
    }

    public List<Role> getUserRoles(long userId) throws LiferayJsonApiException {
        log.info("getUserRoles {} {}",userId);
        String url = URL_GET_USER_ROLES
        					.replace("{userId}", String.valueOf(userId));
        List<Role> respuesta = super.getListFromJson(url, new TypeReference<List<Role>>() {});
        return respuesta;
    }

    public Role getRoleByName(long companyID, String name) throws LiferayJsonApiException {
        log.info("getRoleByName {} {}",companyID,name);
        String url = URL_GET_ROLES_BY_NAME
        					.replace("{companyID}", String.valueOf(companyID))
        					.replace("{name}",name);
        Role respuesta = super.getObjectFromJson(url, Role.class);
        return respuesta;
    }
}