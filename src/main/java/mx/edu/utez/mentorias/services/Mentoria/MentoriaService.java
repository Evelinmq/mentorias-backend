package mx.edu.utez.mentorias.services.Mentoria;


import mx.edu.utez.mentorias.models.EstadoMentoria.BeanEstadoMentoria;
import mx.edu.utez.mentorias.models.EstadoMentoria.EstadoMentoriaRepository;
import mx.edu.utez.mentorias.models.Mentoria.BeanMentoria;
import mx.edu.utez.mentorias.models.Mentoria.MentoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class MentoriaService {

    private final MentoriaRepository mentoriaRepository;
    private final EstadoMentoriaRepository estadoMentoriaRepository;

    public MentoriaService(MentoriaRepository mentoriaRepository,
                           EstadoMentoriaRepository estadoMentoriaRepository) {
        this.mentoriaRepository = mentoriaRepository;
        this.estadoMentoriaRepository = estadoMentoriaRepository;
    }

    // MentoriaService.java
    @Transactional(readOnly = true)
    public List<BeanMentoria> listarProximas() {
        // Asegúrate de que este nombre sea el mismo que en el Repository
        return mentoriaRepository.findMentoriasVigentesYReales(LocalDate.now());
    }


    @Transactional
    public BeanMentoria aceptarMentoria(Long id) {

        BeanMentoria mentoria = mentoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentoría no encontrada"));

        BeanEstadoMentoria estado = estadoMentoriaRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        mentoria.setEstado(estado);

        return mentoriaRepository.save(mentoria);
    }

    @Transactional
    public BeanMentoria cancelarMentoria(Long id) {

        BeanMentoria mentoria = mentoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentoría no encontrada"));

        // 2 = CANCELADA
        BeanEstadoMentoria estado = estadoMentoriaRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Estado CANCELADA no existe"));

        mentoria.setEstado(estado);

        return mentoriaRepository.save(mentoria);
    }

    public List<BeanMentoria> obtenerMentoriasDelMentor(Long mentorId){
        return mentoriaRepository.findByMentor(mentorId);
    }


    @Transactional(readOnly = true)
    public List<BeanMentoria> listarTodas() {
        return mentoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public BeanMentoria buscarPorId(Long id) {
        return mentoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentoría no encontrada con ID: " + id));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public BeanMentoria guardar(BeanMentoria mentoria) {
        return mentoriaRepository.save(mentoria);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public BeanMentoria actualizar(Long id, BeanMentoria datosNuevos) {
        return mentoriaRepository.findById(id).map(mentoria -> {
            mentoria.setFecha(datosNuevos.getFecha());
            mentoria.setHoraInicio(datosNuevos.getHoraInicio());
            mentoria.setHoraFin(datosNuevos.getHoraFin());
            mentoria.setCuatrimestre(datosNuevos.getCuatrimestre());
            mentoria.setCupo(datosNuevos.getCupo());
            mentoria.setEspacio(datosNuevos.getEspacio());
            mentoria.setEstado(datosNuevos.getEstado());
            mentoria.setMentor(datosNuevos.getMentor());
            mentoria.setMateria(datosNuevos.getMateria());
            return mentoriaRepository.save(mentoria);
        }).orElseThrow(() -> new RuntimeException("No se encontró la mentoría para actualizar"));
    }

    public void eliminar(Long id) {
        if (!mentoriaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: La mentoría no existe");
        }
        mentoriaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BeanMentoria> listarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return mentoriaRepository.findByFechaBetween(inicio, fin);
    }

    @Transactional(readOnly = true)
    public List<BeanMentoria> listarPorFecha(LocalDate fecha) {
        return mentoriaRepository.findByFecha(fecha);
    }

    @Transactional(readOnly = true)
    public List<BeanMentoria> buscarConFiltros(LocalDate inicio, LocalDate fin, Long materiaId, Long mentorId) {
        // fechas por defecto si el admin no las envía
        if (inicio == null) inicio = LocalDate.now().minusMonths(1); // Hace un mes
        if (fin == null) fin = LocalDate.now().plusMonths(1);       // En un mes

        return mentoriaRepository.filtrarDinamico(inicio, fin, materiaId, mentorId);
    }

    public List<BeanMentoria> listarDisponibles() {
        return mentoriaRepository.findAllByCupoLessThan(5);
    }

}
