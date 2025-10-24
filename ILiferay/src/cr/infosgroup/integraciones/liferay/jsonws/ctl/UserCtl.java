package cr.infosgroup.integraciones.liferay.jsonws.ctl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cr.infosgroup.integraciones.liferay.ctl.BaseCtl;
import cr.infosgroup.integraciones.liferay.exception.LiferayJsonApiException;
import cr.infosgroup.integraciones.liferay.jsonws.model.User;

public class UserCtl extends BaseCtl {

    private static final Logger log = LogManager.getLogger(UserCtl.class);
    
    private static String SERVICIO = BaseCtl.ROOT_API_JSONWS + "user/";
    private static String URL_GET_USER_BY_EMAIL_ADDRESS = SERVICIO + "get-user-by-email-address/company-id/{companyId}/email-address/{emailAddress}";
    private static String URL_GET_USER_BY_SCREEN_NAME = SERVICIO + "get-user-by-screen-name/company-id/{companyId}/screen-name/{screenName}";
    private static String URL_GET_USER_BY_ID = SERVICIO + "get-user-by-id/user-id/{userId}";

    public UserCtl(String urlRootLiferay, String userLiferay, String passwordLiferay) {
        super(urlRootLiferay, userLiferay, passwordLiferay);
    }

    public User getUserByEmailAddress(long companyId, String emailAddress) throws LiferayJsonApiException {
        log.info("getUserByEmailAddress {} {}",companyId,emailAddress);
        String url = URL_GET_USER_BY_EMAIL_ADDRESS
				.replace("{companyId}", String.valueOf(companyId))
				.replace("{emailAddress}",emailAddress);
        User respuesta = super.getObjectFromJson(url, User.class);
        return respuesta;
    }

    public User getUserByScreenName(long companyId, String screenName) throws LiferayJsonApiException {
        log.info("getUserByScreenName {} {}",companyId,screenName);    	
        String url = URL_GET_USER_BY_SCREEN_NAME
				.replace("{companyId}", String.valueOf(companyId))
				.replace("{screenName}",screenName);        
        User respuesta = super.getObjectFromJson(url, User.class);
        return respuesta;
    }

    public User getUserByUserId(long userId) throws LiferayJsonApiException {
        log.info("getUserByUserId {}",userId);    	
        String url = URL_GET_USER_BY_ID
				.replace("{userId}", String.valueOf(userId));         
        User respuesta = super.getObjectFromJson(url, User.class);
        return respuesta;
    }
}