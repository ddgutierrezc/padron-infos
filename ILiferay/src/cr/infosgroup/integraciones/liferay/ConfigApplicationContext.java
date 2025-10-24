package cr.infosgroup.integraciones.liferay;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApplicationContext {

	@Bean
	public Liferay liferay() {
		String urlRootLiferay = "http://localhost:8080";
		String userLiferay = "usrSistema@liferay.com";
		String passwordLiferay = "infos";
		long companyId = 20097L;
		String authType = "emailAddress";
		return new Liferay(urlRootLiferay, userLiferay, passwordLiferay, companyId, authType);
	}
}
