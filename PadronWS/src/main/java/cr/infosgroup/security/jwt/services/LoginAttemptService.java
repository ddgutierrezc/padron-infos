package cr.infosgroup.security.jwt.services;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginAttemptService {

	@Value("${project.jwt.max_intentos_autenticacion}")
    private int MAX_INTENTOS_AUTENTICACION;
	
	@Value("${project.jwt.duracion_bloqueo_minutos}")
    private int DURACION_BLOQUEO_MINUTOS;
	
    // IP -> [intentos fallidos, timestamp del bloqueo]
    private final ConcurrentHashMap<String, AttemptInfo> attempts = new ConcurrentHashMap<>();

    private String getKey(String userName,String ip) {
    	StringBuffer stringBuffer = new StringBuffer();
    	stringBuffer.append(userName);
    	stringBuffer.append("-");
    	stringBuffer.append(ip);
    	String key = stringBuffer.toString();
        return key;
    }
    
    public void loginSucceeded(String userName,String ip) {
    	String key = this.getKey(userName,ip);
        attempts.remove(key);
    }

    public void loginFailed(String userName,String ip) {
    	String key = this.getKey(userName,ip);
        attempts.compute(key, (k, info) -> {
            if (info == null) {
                return new AttemptInfo(1, 0);
            }
            int newCount = info.attempts + 1;
            long blockTime = newCount >= MAX_INTENTOS_AUTENTICACION ? System.currentTimeMillis() : info.blockedUntil;
            return new AttemptInfo(newCount, blockTime);
        });
    }

    public boolean isBlocked(String userName,String ip) {
    	String key = this.getKey(userName,ip);
        AttemptInfo info = attempts.get(key);
        if (info == null) return false;
        if (info.blockedUntil == 0) return false;
        if (System.currentTimeMillis() > info.blockedUntil + TimeUnit.MINUTES.toMillis(DURACION_BLOQUEO_MINUTOS)) {
            attempts.remove(key);
            return false;
        }
        return true;
    }

    private static class AttemptInfo {
        final int attempts;
        final long blockedUntil;
        AttemptInfo(int attempts, long blockedUntil) {
            this.attempts = attempts;
            this.blockedUntil = blockedUntil;
        }
    }
}