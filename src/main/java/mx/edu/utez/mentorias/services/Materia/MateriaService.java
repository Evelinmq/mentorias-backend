package mx.edu.utez.mentorias.services.Materia;

import mx.edu.utez.mentorias.dto.MateriaDTO;
import mx.edu.utez.mentorias.models.Carrera.BeanCarrera;
import mx.edu.utez.mentorias.models.Carrera.CarreraRepository;
import mx.edu.utez.mentorias.models.Materia.BeanMateria;
import mx.edu.utez.mentorias.models.Materia.MateriaRepository;
import mx.edu.utez.mentorias.models.MateriaCarrera.BeanMateriaCarrera;
import mx.edu.utez.mentorias.models.MateriaCarrera.MateriaCarreraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MateriaService {

    private final CarreraRepository carreraRepository;
    private final MateriaCarreraRepository materiaCarreraRepository;
    private MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository, CarreraRepository carreraRepository, MateriaCarreraRepository materiaCarreraRepository) {
        this.materiaRepository = materiaRepository;
        this.carreraRepository = carreraRepository;
        this.materiaCarreraRepository = materiaCarreraRepository;
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

    @Transactional(readOnly = true)
    public List<MateriaDTO> listarTodasConCarrera() {
        return materiaRepository.findAllMateriasWithCarrera();
    }

    @Transactional(rollbackFor = Exception.class)
    public BeanMateria guardar(MateriaDTO dto) {
        // 1. Guardamos la materia primero
        BeanMateria nuevaMateria = new BeanMateria();
        nuevaMateria.setNombre(dto.getNombre());
        nuevaMateria.setCuatrimestre(dto.getCuatrimestre());
        BeanMateria materiaGuardada = materiaRepository.save(nuevaMateria);

        // 2. Buscamos la carrera
        BeanCarrera carrera = carreraRepository.findById(dto.getCarreraId())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // 3. Creamos la relación en la tabla intermedia
        BeanMateriaCarrera relacion = new BeanMateriaCarrera();
        relacion.setMateria(materiaGuardada);
        relacion.setCarrera(carrera);
        materiaCarreraRepository.save(relacion);

        return materiaGuardada;
    }

    // En MateriaService.java
    @Transactional(rollbackFor = Exception.class)
    public BeanMateria actualizar(Long id, MateriaDTO dto) {
        // 1. Buscar y actualizar los datos básicos de la materia
        BeanMateria materia = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        materia.setNombre(dto.getNombre());
        materia.setCuatrimestre(dto.getCuatrimestre());
        materiaRepository.save(materia);

        // 2. Actualizar la relación con la carrera
        // Buscamos la relación actual en la tabla intermedia
        BeanMateriaCarrera relacion = materiaCarreraRepository.findByMateriaId(id)
                .orElse(new BeanMateriaCarrera()); // Si no existe, creamos una nueva

        // Buscamos la nueva carrera seleccionada
        BeanCarrera nuevaCarrera = carreraRepository.findById(dto.getCarreraId())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        relacion.setMateria(materia);
        relacion.setCarrera(nuevaCarrera);

        materiaCarreraRepository.save(relacion);

        return materia;
    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Long id) {
        // 1. Validar si existe la materia
        if (!materiaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: ID no encontrado");
        }

        // 2. Limpiar la tabla intermedia primero
        materiaCarreraRepository.deleteByMateriaId(id);

        // 3. Borrar la materia físicamente
        materiaRepository.deleteById(id);
    }

}
