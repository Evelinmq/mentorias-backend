package mx.edu.utez.mentorias.services.MateriaCarrera;


import mx.edu.utez.mentorias.models.MateriaCarrera.MateriaCarreraRepository;
import org.springframework.stereotype.Service;

@Service
public class MateriaCarreraService {

    private MateriaCarreraRepository materiaCarreraRepository;

    public MateriaCarreraService(MateriaCarreraRepository materiaCarreraRepository) {
        this.materiaCarreraRepository = materiaCarreraRepository;
    }
}
