package mx.edu.utez.mentorias.services.Mentoria;


import mx.edu.utez.mentorias.models.Mentoria.MentoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class MentoriaService {

    private MentoriaRepository mentoriaRepository;

    public MentoriaService(MentoriaRepository mentoriaRepository) {
        this.mentoriaRepository = mentoriaRepository;
    }
}
