package mx.edu.utez.mentorias.services.EstadoMentoria;


import mx.edu.utez.mentorias.models.EstadoMentoria.BeanEstadoMentoria;
import mx.edu.utez.mentorias.models.EstadoMentoria.EstadoMentoriaRepository;
import mx.edu.utez.mentorias.models.Mentoria.BeanMentoria;
import mx.edu.utez.mentorias.models.Mentoria.MentoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EstadoMentoriaService {

    private final MentoriaRepository mentoriaRepository;
    private EstadoMentoriaRepository estadoMentoriaRepository;

    public EstadoMentoriaService(EstadoMentoriaRepository estadoMentoriaRepository, MentoriaRepository mentoriaRepository) {
        this.estadoMentoriaRepository = estadoMentoriaRepository;
        this.mentoriaRepository = mentoriaRepository;
    }

    @Transactional(readOnly = true)
    public List<BeanEstadoMentoria> listarTodos() {
        return estadoMentoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public BeanEstadoMentoria buscarPorId(Long id) {
        return estadoMentoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado de mentoría no encontrado con ID: " + id));
    }



}
