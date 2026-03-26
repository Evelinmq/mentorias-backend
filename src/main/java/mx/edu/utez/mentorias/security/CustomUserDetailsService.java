package mx.edu.utez.mentorias.security;

import mx.edu.utez.mentorias.models.usuario.BeanUsuario;
import mx.edu.utez.mentorias.models.usuario.UsuarioRepository; // <-- Ruta correcta
import mx.edu.utez.mentorias.models.UsuarioRol.BeanUsuarioRol;
import mx.edu.utez.mentorias.models.UsuarioRol.UsuarioRolRepository; // <-- Ruta correcta

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository, UsuarioRolRepository usuarioRolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioRolRepository = usuarioRolRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        // 1. Buscamos al usuario
        BeanUsuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + correo));

        // 2. Buscamos sus roles en la tabla intermedia
        List<BeanUsuarioRol> rolesDelUsuario = usuarioRolRepository.findByUsuario_Id(usuario.getId());

        // 3. Extraemos el nombre de los roles
        List<GrantedAuthority> permisos = new ArrayList<>();

        for (BeanUsuarioRol usuarioRol : rolesDelUsuario) {
            String nombreDelRol = usuarioRol.getRol().getNombre().toLowerCase();
            permisos.add(new SimpleGrantedAuthority(nombreDelRol));
        }

        // Si no tiene rol, le damos uno por defecto
        if (permisos.isEmpty()) {
            permisos.add(new SimpleGrantedAuthority("aprendiz"));
        }

        // 4. Retornamos el usuario con sus permisos reales
        return new User(
                usuario.getCorreo(),
                usuario.getContrasena(),
                permisos
        );
    }
}