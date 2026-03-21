package mx.edu.utez.mentorias.services.Espacio;

import mx.edu.utez.mentorias.models.Espacio.EspacioRepository;
import org.springframework.stereotype.Service;

@Service
public class EspacioService {

    private EspacioRepository espacioRepository;

    public EspacioService(EspacioRepository espacioRepository) {
        this.espacioRepository = espacioRepository;
    }
}
