package mx.edu.utez.mentorias.config;

import jakarta.websocket.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.autoconfigure.actuate.web.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy; // <--- NUEVO IMPORT
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        // 1. Rutas públicas (Login debe ir primero o muy arriba)
                        .requestMatchers("/api/auth/**", "/api/usuarios/login").permitAll()
                        .requestMatchers("/api-docs/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/api/usuarios/recuperar-password", "/api/usuarios/verificar-codigo", "/api/usuarios/actualizar-password").permitAll()

                        // 2. Reglas por roles
                        .requestMatchers("/api/mentorias/**", "/api/mentorias-usuarios/**").hasAnyRole("MENTOR", "ADMINISTRADOR", "APRENDIZ")
                        .requestMatchers(HttpMethod.GET, "/api/carreras/**", "/api/materias/**", "/api/edificios/**", "/api/espacios/**", "/api/usuarios/**").hasAnyRole("MENTOR", "ADMINISTRADOR", "APRENDIZ")
                        .requestMatchers("/api/carreras/**", "/api/materias/**", "/api/edificios/**", "/api/espacios/**").hasRole("ADMINISTRADOR")

                        // 3. Rutas autenticadas
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/usuarios/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/usuarios/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/reportes/**").authenticated()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        configuration.setAllowCredentials(true); //

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.withRolePrefix("ROLE_") // Importante si usas hasRole
                .role("ADMINISTRADOR").implies("MENTOR")
                .role("MENTOR").implies("APRENDIZ")
                .build();
    }
}