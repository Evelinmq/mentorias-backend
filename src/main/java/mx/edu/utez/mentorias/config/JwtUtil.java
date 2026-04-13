package mx.edu.utez.mentorias.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import mx.edu.utez.mentorias.models.usuario.BeanUsuario;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    // Esta es la firma secreta. En producción debería estar en application.properties
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final String SECRET_KEY = "a80971df26a8c5d90aa493f7a8727b90652a17ede50c50f9a6c29934dc57263f";


    // Tiempo de vida del token: 10 horas
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    public String generateToken(BeanUsuario usuario) {
        // Extraemos los nombres de los roles
        List<String> roles = usuario.getRoles().stream()
                .map(rol -> rol.getNombre().toUpperCase())
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(usuario.getCorreo())
                .claim("roles", roles) // <-- Guardamos la lista de roles
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY)
                .compact();
    }

    // Extraer roles
    public List<String> extractRoles(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roles", List.class);
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
