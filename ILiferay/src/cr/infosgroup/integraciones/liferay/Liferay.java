package cr.infosgroup.integraciones.liferay;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cr.infosgroup.integraciones.liferay.exception.LiferayJsonApiException;
import cr.infosgroup.integraciones.liferay.jsonws.JsonWS;
import cr.infosgroup.integraciones.liferay.jsonws.model.Role;
import cr.infosgroup.integraciones.liferay.jsonws.model.User;

public class Liferay implements ILiferay {

	/**
	 * Manejo de logs
	 */
	private static final Logger log = LogManager.getLogger(Liferay.class);
		
	
	private final String AUTH_TYPE_EMAIL_ADDRESS = "emailAddress";
	private final String AUTH_TYPE_SCREEN_NAME = "screenName";
	private final String AUTH_TYPE_USER_ID = "userId";
	
	private String authType; // emailAddress screenName userId
	private JsonWS jsonWS;	

	public Liferay(
					String urlRootLiferay, 
					String userLiferay, 
					String passwordLiferay, 
					long companyId,
					String authType
				) {
		
		this.authType = authType;
		this.jsonWS = new JsonWS(urlRootLiferay, userLiferay, passwordLiferay, companyId);	
	}
	
	@Override
	public boolean autenticar(String usuario, String password) {
		try {
			if(usuario != null && password != null) {
				if(AUTH_TYPE_EMAIL_ADDRESS.equalsIgnoreCase(this.authType)) {
					//Autentica por emailAddress
					this.jsonWS.autenticarByEmailAddress(usuario, password);
					return true;
				}
				else if(AUTH_TYPE_SCREEN_NAME.equalsIgnoreCase(this.authType)) {
					//Autentica por ScreenName
					this.jsonWS.autenticarByScreenName(usuario, password);				
					return true;
				}
				else if(AUTH_TYPE_USER_ID.equalsIgnoreCase(this.authType)) {
					//Autentica por userId
					Long userId = Long.valueOf(usuario);
					this.jsonWS.autenticarByUserId(userId, password);				
					return true;
				}	
			}
			return false;
		} catch (LiferayJsonApiException e) {
			log.error(e);
			return false;
		}
	}
		
	@Override
	public List<String> findUserRolesLiferay(String usuario) {
		List<String> resultado = new ArrayList<String>();
		try {
			if(AUTH_TYPE_EMAIL_ADDRESS.equalsIgnoreCase(this.authType)) {
				//Busca roles por emailAddress
				resultado = this.jsonWS.findUserRolesByEmailAddress(usuario);
			}
			else if(AUTH_TYPE_SCREEN_NAME.equalsIgnoreCase(this.authType)) {
				//Busca roles por ScreenName		
				resultado = this.jsonWS.findUserRolesByScreenName(usuario);
			}
			else if(AUTH_TYPE_USER_ID.equalsIgnoreCase(this.authType)) {
				//Busca roles por userId
				Long userId = Long.valueOf(usuario);
				resultado = this.jsonWS.findUserRolesByUserId(userId);	
			}
			return resultado;
		} 
		catch (LiferayJsonApiException e) {
			log.error(e);
			return resultado;
		}
	}

	@Override
	public Role getRoleByName(String name) {
		Role resultado = null;
		try {
			resultado = this.jsonWS.getRoleByName(name);
			
			return resultado;
		} 
		catch (LiferayJsonApiException e) {
			log.error(e);
			return resultado;
		}
	}	
	
	@Override
	public User buscarUsuarioPorCorreo(String correo) throws LiferayJsonApiException{
		try {
			return this.jsonWS.buscarUsuarioPorCorreo(correo);
		}catch (LiferayJsonApiException e) {
			log.error(e);
			throw e;
		}
	}
}
