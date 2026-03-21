package mx.edu.utez.mentorias.services.Edificio;

import mx.edu.utez.mentorias.models.Edificio.EdificioRepository;
import org.springframework.stereotype.Service;

@Service
public class EdificioService {

    private EdificioRepository edificioRepository;

    public EdificioService(EdificioRepository edificioRepository) {
        this.edificioRepository = edificioRepository;
    }
}
