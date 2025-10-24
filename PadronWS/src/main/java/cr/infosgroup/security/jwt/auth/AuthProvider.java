package cr.infosgroup.security.jwt.auth;

/**
 * Enum de Proveedores de Autenticacion
 * @author lsanabria
 */
public enum AuthProvider {
	PRUEBA,
	LIFERAY
	;

	public static AuthProvider getAuthProvider(String name) throws Exception {
		AuthProvider authProvider;
		if(AuthProvider.PRUEBA.name().equalsIgnoreCase(name)) {
			authProvider = AuthProvider.PRUEBA;
		}
		else if(AuthProvider.LIFERAY.name().equalsIgnoreCase(name)) {
			authProvider = AuthProvider.LIFERAY;
		}
		else {
			throw new Exception("AuthProvider no soportado");
		}
		return authProvider;
	}
}
