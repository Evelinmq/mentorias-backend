package mx.edu.utez.mentorias.services.Rol;

import mx.edu.utez.mentorias.models.Rol.RolRepository;
import org.springframework.stereotype.Service;

@Service
public class RolService {

    private RolRepository rolRepository;
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

}
