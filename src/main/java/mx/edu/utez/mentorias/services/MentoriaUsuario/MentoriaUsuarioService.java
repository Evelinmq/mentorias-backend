package mx.edu.utez.mentorias.services.MentoriaUsuario;

import mx.edu.utez.mentorias.models.Mentoria.BeanMentoria;
import mx.edu.utez.mentorias.models.Mentoria.MentoriaRepository;
import mx.edu.utez.mentorias.models.MentoriaUsuario.BeanMentoriaUsuario;
import mx.edu.utez.mentorias.models.MentoriaUsuario.MentoriaUsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MentoriaUsuarioService {

    private MentoriaUsuarioRepository mentoriaUsuarioRepository;
    private MentoriaRepository mentoriaRepository;

    public MentoriaUsuarioService(MentoriaUsuarioRepository mentoriaUsuarioRepository, MentoriaRepository mentoriaRepository) { // <--- Agrega el segundo parámetro aquí
        this.mentoriaUsuarioRepository = mentoriaUsuarioRepository;
        this.mentoriaRepository = mentoriaRepository;
    }

    @Transactional(readOnly = true)
    public List<BeanMentoriaUsuario> listarInscripciones() {
        return mentoriaUsuarioRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public BeanMentoriaUsuario inscribir(BeanMentoriaUsuario inscripcion) {

        Long mentoriaId = inscripcion.getMentoria().getId();

        BeanMentoria mentoria = mentoriaRepository.findById(mentoriaId)
                .orElseThrow(() -> new RuntimeException("La mentoría no existe"));

        long inscritosActualmente = mentoriaUsuarioRepository.countByMentoriaId(mentoriaId);

        if (inscritosActualmente >= mentoria.getCupo()) {
            throw new RuntimeException("Lo sentimos, la mentoría '" + mentoria.getMateria().getNombre() +
                    "' ya ha alcanzado su cupo máximo de " + mentoria.getCupo() + " alumnos.");
        }

        return mentoriaUsuarioRepository.save(inscripcion);
    }

    public void cancelarInscripcion(Long id) {
        if (!mentoriaUsuarioRepository.existsById(id)) {
            throw new RuntimeException("No se encontró la inscripción con ID: " + id);
        }
        mentoriaUsuarioRepository.deleteById(id);
    }

}
