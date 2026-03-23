package mx.edu.utez.mentorias.services.Materia;

import mx.edu.utez.mentorias.models.Materia.BeanMateria;
import mx.edu.utez.mentorias.models.Materia.MateriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MateriaService {

    private MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    @Transactional(readOnly = true)
    public List<BeanMateria> listarTodas() {
        return materiaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public BeanMateria buscarPorId(Long id) {
        return materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con ID: " + id));
    }

    @Transactional(rollbackFor = Exception.class)
    public BeanMateria guardar(BeanMateria materia) {
        return materiaRepository.save(materia);
    }

    @Transactional(rollbackFor = Exception.class)
    public BeanMateria actualizar(Long id, BeanMateria materiaActualizada) {
        return materiaRepository.findById(id).map(materia -> {
            materia.setNombre(materiaActualizada.getNombre());
            return materiaRepository.save(materia);
        }).orElseThrow(() -> new RuntimeException("No se puede actualizar: ID no encontrado"));
    }

    public void eliminar(Long id) {
        if (!materiaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: ID no encontrado");
        }
        materiaRepository.deleteById(id);
    }

}
