package mx.edu.utez.mentorias.services.UsuarioRol;

import mx.edu.utez.mentorias.models.UsuarioRol.UsuarioRolRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioRolService {

    private UsuarioRolRepository usuarioRolRepository;

    public UsuarioRolService(UsuarioRolRepository usuarioRolRepository) {
        this.usuarioRolRepository = usuarioRolRepository;
    }

}
