package mx.edu.utez.mentorias.controllers;

import mx.edu.utez.mentorias.dto.MentoriaMovilDTO;
import mx.edu.utez.mentorias.models.Mentoria.BeanMentoria;
import mx.edu.utez.mentorias.services.Mentoria.MentoriaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/mentorias", produces = "application/json")
@CrossOrigin(origins = "http://localhost:5173")
public class MentoriaController {


    private final MentoriaService mentoriaService;

    public MentoriaController(MentoriaService mentoriaService) {
        this.mentoriaService = mentoriaService;
    }

    @GetMapping
    public ResponseEntity<List<BeanMentoria>> listar() {
        return ResponseEntity.ok(mentoriaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeanMentoria> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mentoriaService.buscarPorId(id));
    }

    @GetMapping("/mentor/{id}")
    public ResponseEntity<?> mentoriasDelMentor(@PathVariable Long id){
        return ResponseEntity.ok(mentoriaService.obtenerMentoriasDelMentor(id));
    }

    @PutMapping("/{id}/aceptar")
    public ResponseEntity<BeanMentoria> aceptarMentoria(@PathVariable Long id) {
        return ResponseEntity.ok(mentoriaService.aceptarMentoria(id));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<BeanMentoria> cancelarMentoria(@PathVariable Long id) {
        return ResponseEntity.ok(mentoriaService.cancelarMentoria(id));
    }

    @GetMapping("/proximas")
    public ResponseEntity<List<BeanMentoria>> listarProximas() {
        // Usamos el método del service que ya tiene el filtro de fecha y nombre
        return ResponseEntity.ok(mentoriaService.listarProximas());
    }

    @PostMapping
    public ResponseEntity<BeanMentoria> crear(@RequestBody BeanMentoria mentoria) {
        return new ResponseEntity<>(mentoriaService.guardar(mentoria), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeanMentoria> editar(@PathVariable Long id, @RequestBody BeanMentoria mentoria) {
        return ResponseEntity.ok(mentoriaService.actualizar(id, mentoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mentoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Rango de fechas
    @GetMapping("/rango")
    public ResponseEntity<List<BeanMentoria>> buscarPorRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(mentoriaService.listarPorRangoFechas(inicio, fin));
    }

    // Una sola fecha
    @GetMapping("/fecha")
    public ResponseEntity<List<BeanMentoria>> buscarPorDia(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dia) {
        return ResponseEntity.ok(mentoriaService.listarPorFecha(dia));
    }

    @GetMapping("/busqueda-avanzada")
    public ResponseEntity<List<BeanMentoria>> busquedaAvanzada(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin,
            @RequestParam(required = false) Long materiaId,
            @RequestParam(required = false) Long mentorId) {

        List<BeanMentoria> resultados = mentoriaService.buscarConFiltros(inicio, fin, materiaId, mentorId);
        return ResponseEntity.ok(resultados);
    }

    //movil
    @GetMapping("/movil")
    public List<MentoriaMovilDTO> listarMovil() {

        return mentoriaService.listar().stream().map((BeanMentoria m) -> {
            MentoriaMovilDTO dto = new MentoriaMovilDTO();

            dto.setId(m.getId());
            dto.setFecha(m.getFecha().toString());
            dto.setHoraInicio(m.getHoraInicio().toString());
            dto.setHoraFin(m.getHoraFin().toString());
            dto.setCupo(m.getCupo());

            dto.setEspacio(
                    m.getEspacio() != null ? m.getEspacio().getNombre() : null
            );

            dto.setMateria(
                    m.getMateria() != null ? m.getMateria().getNombre() : null
            );

            return dto;
        }).toList();
    }

    @GetMapping("/movil/mentor/{id}")
    public List<MentoriaMovilDTO> listarPorMentor(@PathVariable Long id) {

        return mentoriaService.obtenerMentoriasDelMentor(id).stream().map(m -> {
            MentoriaMovilDTO dto = new MentoriaMovilDTO();

            dto.setId(m.getId());
            dto.setFecha(m.getFecha().toString());
            dto.setHoraInicio(m.getHoraInicio().toString());
            dto.setHoraFin(m.getHoraFin().toString());
            dto.setCupo(m.getCupo());

            dto.setEspacio(
                    m.getEspacio() != null ? m.getEspacio().getNombre() : null
            );

            dto.setMateria(
                    m.getMateria() != null ? m.getMateria().getNombre() : null
            );

            return dto;
        }).toList();
    }

}