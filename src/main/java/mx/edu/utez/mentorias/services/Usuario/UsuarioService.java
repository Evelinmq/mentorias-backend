package mx.edu.utez.mentorias.services.Usuario;

import mx.edu.utez.mentorias.models.usuario.BeanUsuario;
import mx.edu.utez.mentorias.models.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<BeanUsuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public BeanUsuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    public BeanUsuario guardar(BeanUsuario usuario) {
        // Después aplicar el hashing o encriptación
        return usuarioRepository.save(usuario);
    }

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

}
