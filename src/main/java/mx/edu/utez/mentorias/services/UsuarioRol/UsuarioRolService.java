package mx.edu.utez.mentorias.services.UsuarioRol;

import mx.edu.utez.mentorias.models.UsuarioRol.BeanUsuarioRol;
import mx.edu.utez.mentorias.models.UsuarioRol.UsuarioRolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioRolService {

    private UsuarioRolRepository usuarioRolRepository;

    public UsuarioRolService(UsuarioRolRepository usuarioRolRepository) {
        this.usuarioRolRepository = usuarioRolRepository;
    }

    // Listar todas las asignaciones
    @Transactional(readOnly = true)
    public List<BeanUsuarioRol> listarTodas() {
        return usuarioRolRepository.findAll();
    }

    // ASIGNAR ROL A USUARIO
    @Transactional(rollbackFor = Exception.class)
    public BeanUsuarioRol guardar(BeanUsuarioRol usuarioRol) {
        return usuarioRolRepository.save(usuarioRol);
    }

    // REVOCAR ROL
    public void eliminar(Long id) {
        if (!usuarioRolRepository.existsById(id)) {
            throw new RuntimeException("No existe la asignación de rol con ID: " + id);
        }
        usuarioRolRepository.deleteById(id);
    }

}
