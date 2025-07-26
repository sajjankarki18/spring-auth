package spring_blogs.spring_blogs.config;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtSignToken {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /* generate ACCESS-TOKEN WITH userId and Email */
    public String generateAccessToken(UUID userId, String email) {
        long nowMillis = System.currentTimeMillis();
        long expiryMillis = nowMillis + (1000 * 60 * 60); // 1 hour

        return Jwts.builder().
                claim("userId", userId).
                claim("email", email).
                setIssuedAt(new Date(nowMillis)).
                setExpiration(new Date(expiryMillis)).
                signWith(key).compact();
    }

    public Key getKey() {
        return key;
    }
}
