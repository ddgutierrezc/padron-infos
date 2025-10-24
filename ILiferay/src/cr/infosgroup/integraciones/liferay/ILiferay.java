package cr.infosgroup.integraciones.liferay;

import java.util.List;

import cr.infosgroup.integraciones.liferay.exception.LiferayJsonApiException;
import cr.infosgroup.integraciones.liferay.jsonws.model.Role;
import cr.infosgroup.integraciones.liferay.jsonws.model.User;

public interface ILiferay {

	//JsonWS
	
	/**
	 * 
	 * @param usuario
	 * @param password
	 * @return
	 */
	public boolean autenticar(String usuario, String password);

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public List<String> findUserRolesLiferay(String usuario);

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Role getRoleByName(String name);
		
	/**
	 * 
	 * @param correo
	 * @return
	 * @throws LiferayJsonApiException
	 */
	public User buscarUsuarioPorCorreo(String correo) throws LiferayJsonApiException;
			
}