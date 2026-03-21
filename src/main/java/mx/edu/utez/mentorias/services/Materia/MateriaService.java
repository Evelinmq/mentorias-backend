package mx.edu.utez.mentorias.services.Materia;

import mx.edu.utez.mentorias.models.Materia.MateriaRepository;
import org.springframework.stereotype.Service;

@Service
public class MateriaService {

    private MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

}
