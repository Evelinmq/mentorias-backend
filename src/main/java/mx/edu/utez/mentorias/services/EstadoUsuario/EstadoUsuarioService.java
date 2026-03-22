package mx.edu.utez.mentorias.services.EstadoUsuario;

import mx.edu.utez.mentorias.models.EstadoUsuario.BeanEstadoUsuario;
import mx.edu.utez.mentorias.models.EstadoUsuario.EstadoUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoUsuarioService {

    private EstadoUsuarioRepository estadoUsuarioRepository;

    public EstadoUsuarioService(EstadoUsuarioRepository estadoUsuarioRepository) {
        this.estadoUsuarioRepository = estadoUsuarioRepository;
    }

    public List<BeanEstadoUsuario> listarTodos() {
        return estadoUsuarioRepository.findAll();
    }

    public BeanEstadoUsuario buscarPorId(Long id) {
        return estadoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado de usuario no encontrado con ID: " + id));
    }
}
