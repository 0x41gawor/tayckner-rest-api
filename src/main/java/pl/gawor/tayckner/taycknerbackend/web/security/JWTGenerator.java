package pl.gawor.tayckner.taycknerbackend.web.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;

import java.util.Date;

/**
 * Class that generates JWT.
 */
@Component
public class JWTGenerator {

    public static final String API_SECRET_KEY = "taycknerapikey";

    public static final long TOKEN_VALIDITY = 2 * 69 * 69 * 1000;

    public String generateJWT(UserModel user) {
        long timestamp = System.currentTimeMillis();
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + TOKEN_VALIDITY))
                .claim("userId", user.getId())
                .claim("username", user.getUsername())
                .compact();
    }

}
