package mx.edu.utez.mentorias.services.Edificio;

import mx.edu.utez.mentorias.models.Edificio.BeanEdificio;
import mx.edu.utez.mentorias.models.Edificio.EdificioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdificioService {

    private EdificioRepository edificioRepository;

    public EdificioService(EdificioRepository edificioRepository) {
        this.edificioRepository = edificioRepository;
    }

    // Listar todos los edificios
    public List<BeanEdificio> listarTodos() {
        return edificioRepository.findAll();
    }

    // Buscar un edificio por id
    public BeanEdificio buscarPorId(Long id) {
        return edificioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Edificio no encontrado"));
    }
}
