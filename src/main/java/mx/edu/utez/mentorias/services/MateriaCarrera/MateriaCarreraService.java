package mx.edu.utez.mentorias.services.MateriaCarrera;


import mx.edu.utez.mentorias.models.MateriaCarrera.BeanMateriaCarrera;
import mx.edu.utez.mentorias.models.MateriaCarrera.MateriaCarreraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MateriaCarreraService {

    private MateriaCarreraRepository materiaCarreraRepository;

    public MateriaCarreraService(MateriaCarreraRepository materiaCarreraRepository) {
        this.materiaCarreraRepository = materiaCarreraRepository;
    }

    @Transactional(readOnly = true)
    public List<BeanMateriaCarrera> listarTodas() {
        return materiaCarreraRepository.findAll();
    }

    // Crear vinculación materia con carrera
    @Transactional(rollbackFor = Exception.class)
    public BeanMateriaCarrera guardar(BeanMateriaCarrera union) {
        return materiaCarreraRepository.save(union);
    }

    // Eliminar la vinculación
    public void eliminar(Long id) {
        if (!materiaCarreraRepository.existsById(id)) {
            throw new RuntimeException("La asociación con ID " + id + " no existe.");
        }
        materiaCarreraRepository.deleteById(id);
    }

}
