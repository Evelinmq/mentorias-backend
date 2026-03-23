package mx.edu.utez.mentorias.services.Rol;

import mx.edu.utez.mentorias.models.Rol.BeanRol;
import mx.edu.utez.mentorias.models.Rol.RolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolService {

    private RolRepository rolRepository;
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<BeanRol> listarTodos() {
        return rolRepository.findAll();
    }

    @Transactional(readOnly = true)
    public BeanRol buscarPorId(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
    }

}
