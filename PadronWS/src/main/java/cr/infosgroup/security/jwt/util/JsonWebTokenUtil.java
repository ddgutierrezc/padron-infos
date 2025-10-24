package cr.infosgroup.security.jwt.util;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cr.infosgroup.security.jwt.exception.ErrorSecurity;
import cr.infosgroup.security.jwt.exception.SecurityException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

/**
 * Utilidad para el manejo de JSON Web Tokens (JWT).
 */
@Component
public class JsonWebTokenUtil implements Serializable {

    private static final Logger log = LogManager.getLogger(JsonWebTokenUtil.class);

    @Serial
    private static final long serialVersionUID = -3301605591108950415L;

    public static final String CLAIM_ID = Claims.ID;

    private final String issuer;
    private final long expirationSeconds;
    private final SecretKey secretKey;
    private final JwtParser jwtParser;

    public JsonWebTokenUtil(
            @Value("${project.jwt.signingKey}") String signingKey,
            @Value("${project.jwt.issuer}") String issuer,
            @Value("${project.jwt.expiration}") Long expirationSeconds) {

        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(signingKey));
        this.issuer = issuer;
        this.expirationSeconds = expirationSeconds != null ? expirationSeconds : 3600L; // default 1h
        this.jwtParser = Jwts.parser().verifyWith(secretKey).build();
    }

    // ============================
    //  MÉTODOS PÚBLICOS - Claims
    // ============================

    public String getId(String token) throws SecurityException {
        return getClaim(token, Claims::getId);
    }

    public String getIssuer(String token) throws SecurityException {
        return getClaim(token, Claims::getIssuer);
    }

    public Date getIssuedAt(String token) throws SecurityException {
        return getClaim(token, Claims::getIssuedAt);
    }

    public Date getNotBefore(String token) throws SecurityException {
        return getClaim(token, Claims::getNotBefore);
    }

    public Date getExpiration(String token) throws SecurityException {
        return getClaim(token, Claims::getExpiration);
    }

    public String getSubject(String token) throws SecurityException {
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaimEspecifico(String token, String claimNombre, Class<T> type) throws SecurityException {
        return getClaimsFromToken(token).get(claimNombre, type);
    }

    @SuppressWarnings("unchecked")
    public List<String> getAudience(String token) throws SecurityException {
        Object aud = getClaimsFromToken(token).getAudience();
        if (aud instanceof String s) return List.of(s);
        if (aud instanceof List<?> l) return (List<String>) l;
        return Collections.emptyList();
    }

    // ============================
    //  MÉTODOS PÚBLICOS - Token
    // ============================

    public boolean isValidToken(String token, String id) throws SecurityException {
        return ejecutarConManejoDeSecurityException(() -> {
            String idToken = getClaimsFromToken(token).get(CLAIM_ID, String.class);
            return Objects.equals(idToken, id);
        });
    }

    public String getTokenFromClaims(Map<String, Object> claims) throws SecurityException {
        return ejecutarConManejoDeSecurityException(() -> {
            if (claims == null || !claims.containsKey(CLAIM_ID)) {
                throw new SecurityException(ErrorSecurity.ErrorSecurity_ClaimsIDRequired);
            }
            return buildToken(claims);
        });
    }

    public Claims getClaimsFromToken(String token) throws SecurityException {
        return ejecutarConManejoDeSecurityException(() ->
            jwtParser.parseSignedClaims(token).getPayload()
        );
    }

    public String refreshToken(String token) throws SecurityException {
        return ejecutarConManejoDeSecurityException(() -> buildToken(getClaimsFromToken(token)));
    }

    // ============================
    //  MÉTODOS PRIVADOS
    // ============================

    private <T> T getClaim(String token, Function<Claims, T> resolver) throws SecurityException {
        return resolver.apply(getClaimsFromToken(token));
    }

    private String buildToken(Map<String, ?> claims) throws SecurityException {
        try {
            Instant now = Instant.now();
            Instant expiry = now.plusSeconds(expirationSeconds);

            return Jwts.builder()
                    .claims(claims)
                    .issuer(issuer)
                    .issuedAt(Date.from(now))
                    .expiration(Date.from(expiry))
                    .signWith(secretKey, Jwts.SIG.HS512)
                    .compact();

        } catch (Exception e) {
            log.error("Error generando token JWT", e);
            throw new SecurityException(ErrorSecurity.ErrorSecurity_Exception, e);
        }
    }

    private <T> T ejecutarConManejoDeSecurityException(SecuritySupplier<T> supplier) throws SecurityException {
        try {
            return supplier.get();
        } 
        catch (SecurityException se) {
            throw se; 
        } 
        catch (UnsupportedJwtException e) {
            log.error("Token no soportado", e);
            throw new SecurityException(ErrorSecurity.ErrorSecurity_UnsupportedJwtException, e);
        } 
        catch (MalformedJwtException e) {
            log.error("Token malformado", e);
            throw new SecurityException(ErrorSecurity.ErrorSecurity_MalformedJwtException, e);
        } 
        catch (SignatureException e) {
            log.error("Firma inválida", e);
            throw new SecurityException(ErrorSecurity.ErrorSecurity_SignatureException, e);
        } 
        catch (ExpiredJwtException e) {
            log.error("Token expirado", e);
            throw new SecurityException(ErrorSecurity.ErrorSecurity_ExpiredJwtException, e);
        } 
        catch (Exception e) {
            log.error("Error general en JWT", e);
            throw new SecurityException(ErrorSecurity.ErrorSecurity_Exception, e);
        }
    }

    @FunctionalInterface
    private interface SecuritySupplier<T> {
        T get() throws Exception;
    }
}
