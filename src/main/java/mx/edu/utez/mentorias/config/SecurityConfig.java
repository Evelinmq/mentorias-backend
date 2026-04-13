package mx.edu.utez.mentorias.config;

import org.springframework.beans.factory.annotation.Autowired;
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

                //autorizar las rutas y la ifnormación para el Frontend
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/mentorias/**").hasRole("MENTOR")
                        .requestMatchers("/api/mentorias-usuarios/**").hasRole("MENTOR")
                        .requestMatchers(HttpMethod.GET,"/api/carreras/**", "/api/materias/**", "/api/edificios/**",
                                "/api/espacios/**", "/api/usuarios/**").hasRole("MENTOR")
                        .requestMatchers("/api/carreras/**").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/materias/**").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/edificios/**").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/espacios/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST,"/api/usuarios").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/api/usuarios/**").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/api/usuarios/**").authenticated()
                        .requestMatchers(HttpMethod.GET,"/api/reportes/**").authenticated()
                        .requestMatchers("/api/usuarios/recuperar-password",
                                "/api/usuarios/verificar-codigo",
                                "/api/usuarios/actualizar-password").permitAll()
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
    public RoleHierarchy roleHierarchy(){
        return RoleHierarchyImpl.withDefaultRolePrefix()
                .role("ADMINISTRADOR")
                .implies("MENTOR", "APRENDIZ")
                .role("MENTOR")
                .implies("APRENDIZ")
                .build();
    }
}