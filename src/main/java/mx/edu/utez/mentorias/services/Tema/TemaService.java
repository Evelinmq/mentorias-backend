package mx.edu.utez.mentorias.services.Tema;

import mx.edu.utez.mentorias.models.Mentoria.BeanMentoria;
import mx.edu.utez.mentorias.models.Mentoria.MentoriaRepository;
import mx.edu.utez.mentorias.models.Tema.BeanTema;
import mx.edu.utez.mentorias.models.Tema.TemaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TemaService {

    private TemaRepository temaRepository;

    private final MentoriaRepository mentoriaRepository;

    public TemaService(TemaRepository temaRepository, MentoriaRepository mentoriaRepository) {
        this.temaRepository = temaRepository;
        this.mentoriaRepository = mentoriaRepository;
    }

    @Transactional(readOnly = true)
    public List<BeanTema> listarTodos() {
        return temaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public BeanTema buscarPorId(Long id) {
        return temaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tema no encontrado con ID: " + id));
    }

    @Transactional
    public BeanTema guardar(Long mentoriaId, BeanTema tema) {

        BeanMentoria mentoria = mentoriaRepository.findById(mentoriaId)
                .orElseThrow(() -> new RuntimeException("Mentoría no encontrada"));

        tema.setMentoria(mentoria); // 🔥 AQUÍ VA

        return temaRepository.save(tema);
    }

    public void eliminar(Long id) {
        if (!temaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Tema no existe");
        }
        temaRepository.deleteById(id);
    }

}
