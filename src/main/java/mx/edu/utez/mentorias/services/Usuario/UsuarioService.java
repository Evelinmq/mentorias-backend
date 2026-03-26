package mx.edu.utez.mentorias.services.Usuario;

import mx.edu.utez.mentorias.contollers.user.dto.CreateUserDTO;
import mx.edu.utez.mentorias.contollers.user.dto.GetMentorByNameDTO;
import mx.edu.utez.mentorias.contollers.user.dto.UserForClientDTO;
import mx.edu.utez.mentorias.mappers.UserMapper;
import mx.edu.utez.mentorias.models.EstadoUsuario.BeanEstadoUsuario;
import mx.edu.utez.mentorias.models.usuario.BeanUsuario;
import mx.edu.utez.mentorias.models.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    private UserMapper userMapper;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            UserMapper userMapper
    ) {
        this.usuarioRepository = usuarioRepository;
        this.userMapper = userMapper;
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
        BeanUsuario newUser = userMapper.createUserToBean(payload);

        return usuarioRepository.saveAndFlush(newUser);
    }

    @Transactional(rollbackFor = Exception.class)
    public BeanUsuario guardar(BeanUsuario usuario) {

        if (usuario.getEstado() == null) {
            BeanEstadoUsuario estado = new BeanEstadoUsuario();
            estado.setId(1L);
            usuario.setEstado(estado);
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public BeanUsuario actualizar(Long id, BeanUsuario datosNuevos) {
        return usuarioRepository.findById(id).map(usuario -> {

            usuario.setNombre(datosNuevos.getNombre());
            usuario.setApellidos(datosNuevos.getApellidos());
            usuario.setCorreo(datosNuevos.getCorreo());
            if (datosNuevos.getEstado() != null) {
                usuario.setEstado(datosNuevos.getEstado());
            }
            if (datosNuevos.getCarrera() != null) {
                usuario.setCarrera(datosNuevos.getCarrera());
            }

            if (datosNuevos.getFoto() != null) {
                usuario.setFoto(datosNuevos.getFoto());
            }

            return usuarioRepository.save(usuario);

        }).orElseThrow(() -> new RuntimeException("No se encontró el usuario"));
    }

    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Usuario no existe");
        }
        usuarioRepository.deleteById(id);
    }

}
