package mx.edu.utez.mentorias.services.Usuario;

import mx.edu.utez.mentorias.contollers.user.dto.*;
import mx.edu.utez.mentorias.mappers.UserMapper;
import mx.edu.utez.mentorias.models.EstadoUsuario.BeanEstadoUsuario;
import mx.edu.utez.mentorias.models.usuario.BeanUsuario;
import mx.edu.utez.mentorias.models.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
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

    @Transactional(rollbackFor = Exception.class)
    public BeanUsuario createUser(CreateUserDTO payload) {
        BeanUsuario newUser = userMapper.createUserToBean(payload);

        if (newUser.getContrasena() != null && !newUser.getContrasena().isEmpty()) {
            newUser.setContrasena(passwordEncoder.encode(newUser.getContrasena()));
        }

        if (newUser.getEstado() == null) {
            BeanEstadoUsuario estadoPendiente = new BeanEstadoUsuario();
            estadoPendiente.setId(3L);
            newUser.setEstado(estadoPendiente);
        }

        return usuarioRepository.save(newUser);
    }

    @Transactional(readOnly = true)
    public List<UserForClientDTO> listarPorEstado(String estado) {
        return userMapper.usersToUserDtos(usuarioRepository.findAllByEstadoNombre(estado));
    }

    @Transactional(readOnly = true)
    public List<UserForClientDTO> listarTodos() {
        List<BeanUsuario> usuarios = usuarioRepository.findAll();
        return userMapper.usersToUserDtos(usuarios);
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


    @Transactional(rollbackFor = Exception.class)
    public BeanUsuario actualizar(Long id, BeanUsuario datosNuevos) {
        BeanUsuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        existente.setNombre(datosNuevos.getNombre());
        existente.setApellidoP(datosNuevos.getApellidoP());
        existente.setApellidoM(datosNuevos.getApellidoM());
        existente.setCorreo(datosNuevos.getCorreo());

        existente.setCarrera(datosNuevos.getCarrera());
        existente.setRoles(datosNuevos.getRoles());

        if (datosNuevos.getEstado() != null) {
            existente.setEstado(datosNuevos.getEstado());
        }

        if (datosNuevos.getContrasena() != null && !datosNuevos.getContrasena().isEmpty()) {
            existente.setContrasena(passwordEncoder.encode(datosNuevos.getContrasena()));
        }

        return usuarioRepository.save(existente);
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

        //se verifica el estado
        if (usuario.getEstado() == null || usuario.getEstado().getId() != 1L) {
            if (usuario.getEstado() != null && usuario.getEstado().getId() == 3L) {
                throw new RuntimeException("Tu cuenta está pendiente de aprobación por un administrador.");
            }
            throw new RuntimeException("Tu cuenta no está activa.");
        }

        // 5. Extraer el rol
        String nombreRol = "aprendiz"; // valor por defecto
        if (usuario.getRoles() != null && !usuario.getRoles().isEmpty()) {
            nombreRol = usuario.getRoles().get(0).getNombre();
        }

        // 6. Construir el DTO que pide el constructor (Long, String, String, String)
        return new LoginResponseDTO(
                usuario.getId(),
                usuario.getNombre() + " " + usuario.getApellidoP() + " " + usuario.getApellidoM(),
                usuario.getCorreo(),
                nombreRol.toLowerCase()
        );
    }

}
