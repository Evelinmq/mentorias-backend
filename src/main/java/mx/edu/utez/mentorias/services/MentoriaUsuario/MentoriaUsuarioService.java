package mx.edu.utez.mentorias.services.MentoriaUsuario;

import mx.edu.utez.mentorias.models.MentoriaUsuario.MentoriaUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class MentoriaUsuarioService {

    private MentoriaUsuarioRepository mentoriaUsuarioRepository;

    public MentoriaUsuarioService(MentoriaUsuarioRepository mentoriaUsuarioRepository) {
        this.mentoriaUsuarioRepository = mentoriaUsuarioRepository;
    }

}
