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
    private final TemaRepository temaRepository; // 🔥 IMPORTANTE

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

    // 🔥 MÉTODO CLAVE (CON TEMA)
    @Transactional(rollbackFor = Exception.class)
    public BeanMentoriaUsuario inscribir(BeanMentoriaUsuario inscripcion, String temaTexto) {

        // 1. Obtener mentoría
        Long mentoriaId = inscripcion.getMentoria().getId();
        BeanMentoria mentoria = mentoriaRepository.findById(mentoriaId)
                .orElseThrow(() -> new RuntimeException("La mentoría no existe"));

        // 2. Obtener usuario
        Long usuarioId = inscripcion.getUsuario().getId();
        BeanUsuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));

        // 3. Validar cupo
        long inscritos = mentoriaUsuarioRepository.countByMentoriaId(mentoriaId);
        if (inscritos >= mentoria.getCupo()) {
            throw new RuntimeException("Cupo lleno");
        }

        // 4. Asignar objetos reales
        inscripcion.setMentoria(mentoria);
        inscripcion.setUsuario(usuario);

        // 5. 🔥 CREAR TEMA
        if (temaTexto != null && !temaTexto.trim().isEmpty()) {
            BeanTema tema = new BeanTema();
            tema.setNombre(temaTexto);
            tema.setMentoria(mentoria);

            temaRepository.save(tema);
        }

        // 6. Guardar inscripción
        return mentoriaUsuarioRepository.save(inscripcion);
    }

    public void cancelarInscripcion(Long id) {
        if (!mentoriaUsuarioRepository.existsById(id)) {
            throw new RuntimeException("No se encontró la inscripción con ID: " + id);
        }
        mentoriaUsuarioRepository.deleteById(id);
    }
}