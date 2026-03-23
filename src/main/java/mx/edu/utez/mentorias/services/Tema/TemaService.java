package mx.edu.utez.mentorias.services.Tema;

import mx.edu.utez.mentorias.models.Tema.BeanTema;
import mx.edu.utez.mentorias.models.Tema.TemaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TemaService {

    private TemaRepository temaRepository;

    public TemaService(TemaRepository temaRepository) {
        this.temaRepository = temaRepository;
    }

    @Transactional(readOnly = true)
    public List<BeanTema> listarTodos() {
        return temaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public BeanTema buscarPorId(Long id) {
        return temaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tema no encontrado con ID: " + id));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public BeanTema guardar(BeanTema tema) {
        return temaRepository.save(tema);
    }

    public void eliminar(Long id) {
        if (!temaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Tema no existe");
        }
        temaRepository.deleteById(id);
    }

}
