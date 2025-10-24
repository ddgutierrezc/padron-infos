package cr.infosgroup.integraciones.liferay.jsonws;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cr.infosgroup.comun.json.JsonUtil;
import cr.infosgroup.integraciones.liferay.ctl.BaseCtl;
import cr.infosgroup.integraciones.liferay.exception.LiferayJsonApiException;
import cr.infosgroup.integraciones.liferay.jsonws.ctl.RoleCtl;
import cr.infosgroup.integraciones.liferay.jsonws.ctl.UserCtl;
import cr.infosgroup.integraciones.liferay.jsonws.model.Role;
import cr.infosgroup.integraciones.liferay.jsonws.model.User;

public class JsonWS extends BaseCtl {

    private static final Logger log = LogManager.getLogger(JsonWS.class);

    private final long companyId;
    private final UserCtl userCtl;
    private final RoleCtl roleCtl;


    public JsonWS(String urlRootLiferay, String userLiferay, String passwordLiferay, long companyId) {
        super(urlRootLiferay, userLiferay, passwordLiferay);
        this.companyId = companyId;
        this.userCtl = new UserCtl(urlRootLiferay, userLiferay, passwordLiferay);
        this.roleCtl = new RoleCtl(urlRootLiferay, userLiferay, passwordLiferay);
    }

    // <editor-fold desc="Metodos de autenticacion">
    //ESTOS CASOS SON PARTICULARES PORQUE USAN LOS CREDENCIALES DEL USUARIO, NO EL USUARIO DE SISTEMA <INICIO>
    public void autenticarByEmailAddress(String emailAddress, String password) throws LiferayJsonApiException {
        String autenticacionByEmailAddress = "/api/jsonws/user/get-user-by-email-address/company-id/{companyId}/email-address/{emailAddress}";
    	String path = autenticacionByEmailAddress
    						.replace("{companyId}", String.valueOf(this.companyId))
    						.replace("{emailAddress}", emailAddress);
        this.autenticar(path, emailAddress, password, "emailAddress");
    }

    public void autenticarByScreenName(String screenName, String password) throws LiferayJsonApiException {
        String autenticarByScreenName = "/api/jsonws/user/get-user-by-screen-name/company-id/{companyId}/screen-name/{screenName}";
    	String path = autenticarByScreenName
				.replace("{companyId}", String.valueOf(this.companyId))
				.replace("{screenName}", screenName);
        this.autenticar(path, screenName, password, "screenName");
    }

    public void autenticarByUserId(Long userId, String password) throws LiferayJsonApiException {
        String autenticarByUserId = "/api/jsonws/user/get-user-by-id/user-id/{userId}";
    	String path = autenticarByUserId
				.replace("{userId}", String.valueOf(userId));
        this.autenticar(path, userId.toString(), password, "userId");
    }

    private void autenticar(String path, String identifier, String password, String tipoIdentificador) throws LiferayJsonApiException {
        try {
            String authorization = super.buildAuthorization(identifier, password);
            String jsonResponse = super.httpGet(authorization, path);
            User user = JsonUtil.getObject(jsonResponse, User.class);
            log.info("Se autentica por {} usuario: {} {} {}", tipoIdentificador, user.getEmailAddress(), user.getScreenName(), user.getUserId());
        } catch (LiferayJsonApiException ex) {
            log.info("Fallo autenticacion por {}: {} httpStatusCode {}", tipoIdentificador, identifier, ex.getStatusCode());
            throw ex;
        } catch (Exception e) {
            log.info("Fallo autenticacion por {}: {}", tipoIdentificador, identifier);
            throw new LiferayJsonApiException();
        }
    }
    // </editor-fold>

    @FunctionalInterface
    private interface UserProvider {
        User get() throws LiferayJsonApiException;
    }
    
    private List<String> findUserRoles(UserProvider userProvider, String identifier) throws LiferayJsonApiException {
        try {
            User user = userProvider.get();
            List<Role> roles = this.roleCtl.getUserRoles(user.getUserId());
            List<String> roleNames = new ArrayList<>();
            for (Role role : roles) {
                roleNames.add(role.getName());
            }
            return roleNames;
        } catch (LiferayJsonApiException ex) {
            log.error("No se logra recuperar lista de roles del usuario {} httpStatusCode {} al consultar servicio.", identifier, ex.getStatusCode());
            throw ex;
        } catch (Exception e) {
            log.error("No se logra recuperar lista de roles del usuario {}.", identifier);
            throw new LiferayJsonApiException();
        }
    }
    
    public List<String> findUserRolesByEmailAddress(String emailAddress) throws LiferayJsonApiException {
        return findUserRoles(() -> this.userCtl.getUserByEmailAddress(companyId, emailAddress), emailAddress);
    }

    public List<String> findUserRolesByScreenName(String screenName) throws LiferayJsonApiException {
        return findUserRoles(() -> this.userCtl.getUserByScreenName(companyId, screenName), screenName);
    }

    public List<String> findUserRolesByUserId(Long userId) throws LiferayJsonApiException {
        return findUserRoles(() -> this.userCtl.getUserByUserId(userId), userId.toString());
    }

    public Role getRoleByName(String name) throws LiferayJsonApiException {
        try {
            return this.roleCtl.getRoleByName(companyId, name);
        } catch (LiferayJsonApiException ex) {
            log.error("No se logra recuperar rol {} httpStatusCode {} al consultar servicio.", name, ex.getStatusCode());
            throw ex;
        } catch (Exception e) {
            log.error("No se logra recuperar rol {}.", name);
            throw new LiferayJsonApiException();
        }
    }

    public User buscarUsuarioPorCorreo(String correo) throws LiferayJsonApiException {
        try {
            return this.userCtl.getUserByEmailAddress(companyId, correo);
        } 
        catch (LiferayJsonApiException e) {
            log.info("NO EXISTE USUARIO {}", correo);
            throw new LiferayJsonApiException();
        }
    }
}
