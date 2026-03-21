package mx.edu.utez.mentorias.services.EstadoUsuario;

import mx.edu.utez.mentorias.models.EstadoUsuario.EstadoUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class EstadoUsuarioService {

    private EstadoUsuarioRepository estadoUsuarioRepository;

    public EstadoUsuarioService(EstadoUsuarioRepository estadoUsuarioRepository) {
        this.estadoUsuarioRepository = estadoUsuarioRepository;
    }
}
