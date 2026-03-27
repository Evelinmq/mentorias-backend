package mx.edu.utez.mentorias.services.Usuario;

import mx.edu.utez.mentorias.contollers.user.dto.*;
import mx.edu.utez.mentorias.mappers.UserMapper;
import mx.edu.utez.mentorias.models.usuario.BeanUsuario;
import mx.edu.utez.mentorias.models.usuario.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<UserForClientDTO> listarTodos() {
        List<BeanUsuario> usuarios = usuarioRepository.findAll();

        List<UserForClientDTO> usuariosDTO = userMapper.usersToUserDtos(usuarios);

        return usuariosDTO;
    }

    @Transactional(readOnly = true)
    public UserForClientDTO buscarPorId(Long id) {
        Optional<BeanUsuario> user = usuarioRepository.findById(id);

        return userMapper.userToUserDto(user.get());
    }

    @Transactional(readOnly = true)
    public UserForClientDTO getMentorByName(GetMentorByNameDTO payload) {
        BeanUsuario user = usuarioRepository.findMentorByNombre(payload.getNombre());
        return userMapper.userToUserDto(user);
    }

    public Object createUser(CreateUserDTO payload) {
        // Validaciones para crear un usuario

        // Mapear los datos del dto de creación a una entidad usuario que podamos registrar
        BeanUsuario newUser = userMapper.createUserToBean(payload);

        return usuarioRepository.saveAndFlush(newUser);
    }

    @Transactional(rollbackFor = Exception.class)
    public BeanUsuario guardar(BeanUsuario usuario) {
        // Después aplicar el hashing o encriptación
        usuario.setContrasena(usuario.getContrasena());
        return usuarioRepository.save(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public BeanUsuario actualizar(Long id, BeanUsuario datosNuevos) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(datosNuevos.getNombre());
            usuario.setApellidos(datosNuevos.getApellidos());
            usuario.setCorreo(datosNuevos.getCorreo());
            // Solo actualizamos la foto si se requiere
            if (datosNuevos.getFoto() != null) {
                usuario.setFoto(datosNuevos.getFoto());
            }
            usuario.setEstado(datosNuevos.getEstado());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("No se encontró el usuario para actualizar"));
    }

    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Usuario no existe");
        }
        usuarioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public LoginResponseDTO login(LoginDTO loginDTO) {
        // 3. Buscar por correo usando tu BeanUsuario
        BeanUsuario usuario = usuarioRepository.findByCorreo(loginDTO.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 4. Comparar contraseñas encriptadas
        if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // 5. Extraer el rol
        String nombreRol = "aprendiz"; // valor por defecto
        if (usuario.getRoles() != null && !usuario.getRoles().isEmpty()) {
            nombreRol = usuario.getRoles().get(0).getNombre();
        }

        // 6. Construir el DTO que pide el constructor (Long, String, String, String)
        return new LoginResponseDTO(
                usuario.getId(),
                usuario.getNombre() + " " + usuario.getApellidos(),
                usuario.getCorreo(),
                nombreRol.toLowerCase()
        );
    }

}
