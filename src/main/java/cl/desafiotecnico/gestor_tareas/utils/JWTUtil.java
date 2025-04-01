package cl.desafiotecnico.gestor_tareas.utils;

import cl.desafiotecnico.gestor_tareas.exception.BadRequestException;
import cl.desafiotecnico.gestor_tareas.exception.ResourceNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    private final Key key = Keys.hmacShaKeyFor("HolaSoyUnKeyParaUnaPruebaTecnica".getBytes(StandardCharsets.UTF_8));
    private final long jwtExpirationMs = 86400000; // 24 hours

    /**
     * Generates a JWT token for the given username.
     * @param username the username to include in the token.
     * @return a JWT token string.
     */
    public String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key)
                .compact();
    }

    /**
     * Validates the given JWT token.
     * @param token the JWT token to validate.
     * @return true if valid, false otherwise.
     */
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    /**
     * Extracts the username from the given JWT token.
     * @param token the JWT token.
     * @return the username contained in the token.
     */
    public String getUsernameFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}

