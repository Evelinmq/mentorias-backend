package mx.edu.utez.mentorias.services.Espacio;

import mx.edu.utez.mentorias.models.Espacio.BeanEspacio;
import mx.edu.utez.mentorias.models.Espacio.EspacioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspacioService {

    private EspacioRepository espacioRepository;

    public EspacioService(EspacioRepository espacioRepository) {
        this.espacioRepository = espacioRepository;
    }

    public List<BeanEspacio> listarTodos() {
        return espacioRepository.findAll();
    }

    public BeanEspacio buscarPorId(Long id) {
        return espacioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espacio no encontrado con ID: " + id));
    }

}
