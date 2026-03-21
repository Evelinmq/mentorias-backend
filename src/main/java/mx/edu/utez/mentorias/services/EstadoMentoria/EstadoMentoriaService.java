package mx.edu.utez.mentorias.services.EstadoMentoria;


import mx.edu.utez.mentorias.models.EstadoMentoria.EstadoMentoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class EstadoMentoriaService {

    private EstadoMentoriaRepository estadoMentoriaRepository;

    public EstadoMentoriaService(EstadoMentoriaRepository estadoMentoriaRepository) {
        this.estadoMentoriaRepository = estadoMentoriaRepository;
    }

}
