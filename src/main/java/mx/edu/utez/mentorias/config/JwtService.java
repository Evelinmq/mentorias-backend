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
public class JwtService {

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final String SECRET_KEY = "a80971df26a8c5d90aa493f7a8727b90652a17ede50c50f9a6c29934dc57263f";

    public String generateToken(BeanUsuario usuario) {
        List<String> roles = usuario.getRoles().stream()
                .map(rol -> rol.getNombre().toUpperCase())
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(usuario.getCorreo())
                .claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(SignatureAlgorithm.HS256,  SECRET_KEY)
                .compact();
    }
}