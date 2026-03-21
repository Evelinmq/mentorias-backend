package mx.edu.utez.mentorias.services.Tema;

import mx.edu.utez.mentorias.models.Tema.TemaRepository;
import org.springframework.stereotype.Service;

@Service
public class TemaService {

    private TemaRepository temaRepository;

    public TemaService(TemaRepository temaRepository) {
        this.temaRepository = temaRepository;
    }

}
