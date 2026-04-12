package mx.edu.utez.mentorias.services.Usuario;

import mx.edu.utez.mentorias.controllers.user.dto.*;
import mx.edu.utez.mentorias.mappers.UserMapper;
import mx.edu.utez.mentorias.models.EstadoUsuario.BeanEstadoUsuario;
import mx.edu.utez.mentorias.models.EstadoUsuario.EstadoUsuarioRepository;
import mx.edu.utez.mentorias.models.Rol.BeanRol;
import mx.edu.utez.mentorias.models.Rol.RolRepository;
import mx.edu.utez.mentorias.models.usuario.BeanUsuario;
import mx.edu.utez.mentorias.models.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private EstadoUsuarioRepository estadoUsuarioRepository;
    private final RolRepository rolRepository;
    private final JavaMailSender mailSender;
    private static final Map<String, String> codigosTemporales = new HashMap<>();

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder,
            EstadoUsuarioRepository estadoUsuarioRepository,
            RolRepository rolRepository,
            JavaMailSender mailSender //Si sale error en esta linea por alguna razón ignorenlo, en realidad si sirve
    ) {
        this.usuarioRepository = usuarioRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.estadoUsuarioRepository = estadoUsuarioRepository;
        this.rolRepository = rolRepository;
        this.mailSender = mailSender;
    }

    @Transactional(rollbackFor = Exception.class)
    public BeanUsuario createUser(CreateUserDTO payload) {

        BeanUsuario newUser = userMapper.createUserToBean(payload);

        // contraseña
        if (newUser.getContrasena() != null && !newUser.getContrasena().isEmpty()) {
            newUser.setContrasena(passwordEncoder.encode(newUser.getContrasena()));
        }

        // estado REAL desde BD
        BeanEstadoUsuario estado = estadoUsuarioRepository.findById(3L)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        newUser.setEstado(estado);

        // roles reales desde BD
        if (payload.getRolesIds() != null && !payload.getRolesIds().isEmpty()) {
            List<BeanRol> roles = rolRepository.findAllById(payload.getRolesIds());
            newUser.setRoles(roles);
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


    @Transactional
    public BeanUsuario actualizar(Long id, BeanUsuario datosNuevos) {

        BeanUsuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existente.setNombre(datosNuevos.getNombre());
        existente.setApellidoP(datosNuevos.getApellidoP());
        existente.setApellidoM(datosNuevos.getApellidoM());
        existente.setCorreo(datosNuevos.getCorreo());

        existente.setCarrera(datosNuevos.getCarrera());

        // roles desde BD (NO directo)
        if (datosNuevos.getRoles() != null) {
            existente.getRoles().clear();

            List<Long> idsRoles = datosNuevos.getRoles()
                    .stream()
                    .map(BeanRol::getId)
                    .toList();

            List<BeanRol> roles = rolRepository.findAllById(idsRoles);

            existente.getRoles().addAll(roles);
        }

        // estado desde BD
        if (datosNuevos.getEstado() != null) {
            BeanEstadoUsuario estado = estadoUsuarioRepository
                    .findById(datosNuevos.getEstado().getId())
                    .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

            existente.setEstado(estado);
        }

        // contraseña
        if (datosNuevos.getContrasena() != null && !datosNuevos.getContrasena().isEmpty()) {
            existente.setContrasena(passwordEncoder.encode(datosNuevos.getContrasena()));
        }

        return usuarioRepository.save(existente);
    }


    @Transactional
    public void cambiarEstado(Long id, Long idNuevoEstado) {
        usuarioRepository.updateEstado(id, idNuevoEstado);
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
            nombreRol = usuario.getRoles().stream()
                    .map(rol -> rol.getNombre().toLowerCase())
                    .collect(Collectors.joining(","));
        }

        // 6. Construir el DTO que pide el constructor (Long, String, String, String)
        return new LoginResponseDTO(
                usuario.getId(),
                usuario.getNombre() + " " + usuario.getApellidoP() + " " + usuario.getApellidoM(),
                usuario.getCorreo(),
                nombreRol.toLowerCase()
        );
    }

    public boolean verificarCodigo(String correo, String codigo) {
        String codigoEnMemoria = codigosTemporales.get(correo);
        System.out.println("DEBUG: Correo buscado: " + correo);
        System.out.println("DEBUG: Código en memoria: " + codigoEnMemoria);
        System.out.println("DEBUG: Código recibido del front: " + codigo);

        return codigo != null && codigo.equals(codigoEnMemoria);
    }

    @Transactional
    public void actualizarPassword(String correo, String nuevaPassword) {
        // Buscamos al usuario por el correo que traemos desde el flujo
        BeanUsuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Encriptamos la nueva contraseña
        usuario.setContrasena(passwordEncoder.encode(nuevaPassword));

        // Guardamos los cambios
        usuarioRepository.save(usuario);

        // Limpiar el código temporal para que no se pueda usar de nuevo
        codigosTemporales.remove(correo);
    }

    public void generarYEnviarCodigo(String correo) {
        // 1. Validar usuario
        usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Generar código
        String codigo = String.valueOf((int)(Math.random() * 90000) + 10000);
        codigosTemporales.put(correo, codigo);

        // 3. ENVIAR CORREO REAL
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setFrom("mentorias.academicas.utez@gmail.com");
            mensaje.setTo(correo);
            mensaje.setSubject("Código de Verificación - Mentorias Académicas");
            mensaje.setText(
                    "Hola,\n\n" +
                            "Recibimos una solicitud para restablecer la contraseña de tu cuenta en Mentorías Académicas.\n\n" +
                            "Tu código de verificación es:\n\n" +
                            codigo + "\n\n" +
                            "Este código es válido por tiempo limitado. Si no solicitaste este cambio, puedes ignorar este mensaje.\n\n" +
                            "Saludos,\n" +
                            "Equipo de Mentorías Académicas"
            );

            mailSender.send(mensaje);
        } catch (Exception e) {
            // Si falla, imprime el error completo en la consola para ver el motivo exacto
            e.printStackTrace();
            throw new RuntimeException("Error al enviar: " + e.getMessage());
        }
    }

}
