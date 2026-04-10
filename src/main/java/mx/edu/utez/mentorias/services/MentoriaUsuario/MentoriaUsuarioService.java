package mx.edu.utez.mentorias.services.MentoriaUsuario;

import mx.edu.utez.mentorias.models.Mentoria.BeanMentoria;
import mx.edu.utez.mentorias.models.Mentoria.MentoriaRepository;
import mx.edu.utez.mentorias.models.MentoriaUsuario.BeanMentoriaUsuario;
import mx.edu.utez.mentorias.models.MentoriaUsuario.MentoriaUsuarioRepository;
import mx.edu.utez.mentorias.models.Tema.BeanTema;
import mx.edu.utez.mentorias.models.Tema.TemaRepository;
import mx.edu.utez.mentorias.models.usuario.BeanUsuario;
import mx.edu.utez.mentorias.models.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MentoriaUsuarioService {

    private final MentoriaUsuarioRepository mentoriaUsuarioRepository;
    private final MentoriaRepository mentoriaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TemaRepository temaRepository;

    public MentoriaUsuarioService(MentoriaUsuarioRepository mentoriaUsuarioRepository,
                                  MentoriaRepository mentoriaRepository,
                                  UsuarioRepository usuarioRepository,
                                  TemaRepository temaRepository) {
        this.mentoriaUsuarioRepository = mentoriaUsuarioRepository;
        this.mentoriaRepository = mentoriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.temaRepository = temaRepository;
    }

    @Transactional(readOnly = true)
    public List<BeanMentoriaUsuario> listarInscripciones() {
        return mentoriaUsuarioRepository.findAll();
    }

    public BeanMentoriaUsuario inscribir(BeanMentoriaUsuario inscripcion, String tema) {

        BeanMentoria mentoria = mentoriaRepository.findById(inscripcion.getMentoria().getId())
                .orElseThrow(() -> new RuntimeException("Mentoría no encontrada"));

        if (mentoria.getAlumnos() == null || mentoria.getAlumnos().isEmpty()) {
            BeanTema nuevoTema = new BeanTema();
            nuevoTema.setNombre(tema);
            nuevoTema.setMentoria(mentoria);
            temaRepository.save(nuevoTema);
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