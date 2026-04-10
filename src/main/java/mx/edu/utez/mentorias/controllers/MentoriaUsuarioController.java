package mx.edu.utez.mentorias.controllers;

import mx.edu.utez.mentorias.models.Mentoria.BeanMentoria;
import mx.edu.utez.mentorias.models.MentoriaUsuario.BeanMentoriaUsuario;
import mx.edu.utez.mentorias.services.MentoriaUsuario.MentoriaUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mentorias-usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class MentoriaUsuarioController {

    private final MentoriaUsuarioService mentoriaUsuarioService;

    public MentoriaUsuarioController(MentoriaUsuarioService mentoriaUsuarioService) {
        this.mentoriaUsuarioService = mentoriaUsuarioService;
    }

    @GetMapping
    public ResponseEntity<List<BeanMentoriaUsuario>> listar() {
        return ResponseEntity.ok(mentoriaUsuarioService.listarInscripciones());
    }

    @PostMapping
    public ResponseEntity<BeanMentoriaUsuario> inscribir(
            @RequestBody BeanMentoriaUsuario inscripcion,
            @RequestParam String tema
    ) {
        return new ResponseEntity<>(
                mentoriaUsuarioService.inscribir(inscripcion, tema),
                HttpStatus.CREATED
        );
    }

    // Agrega este nuevo endpoint
    @GetMapping("/conteo")
    public ResponseEntity<Map<Long, Long>> conteo() {
        List<BeanMentoriaUsuario> inscripciones = mentoriaUsuarioService.listarInscripciones();
        Map<Long, Long> conteo = inscripciones.stream()
                .collect(Collectors.groupingBy(
                        i -> i.getMentoria().getId(),
                        Collectors.counting()
                ));
        return ResponseEntity.ok(conteo);
    }

    // MentoriaUsuarioController.java
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Long>> mentoriaIdsPorUsuario(@PathVariable Long usuarioId) {
        List<Long> ids = mentoriaUsuarioService.listarInscripciones()
                .stream()
                .filter(i -> i.getUsuario().getId().equals(usuarioId))
                .map(i -> i.getMentoria().getId())
                .collect(Collectors.toList());
        return ResponseEntity.ok(ids);
    }

    @GetMapping("/usuario/{usuarioId}/detalle")
    public ResponseEntity<List<BeanMentoria>> mentoriasPorUsuario(@PathVariable Long usuarioId) {
        List<BeanMentoria> mentorias = mentoriaUsuarioService.listarInscripciones()
                .stream()
                .filter(i -> i.getUsuario().getId().equals(usuarioId))
                .map(i -> i.getMentoria())
                .collect(Collectors.toList());
        return ResponseEntity.ok(mentorias);
    }
 //historial
    @GetMapping("/usuario/{usuarioId}/historial")
    public ResponseEntity<List<BeanMentoria>> historialPorUsuario(@PathVariable Long usuarioId) {
        List<BeanMentoria> mentorias = mentoriaUsuarioService.listarInscripciones()
                .stream()
                .filter(i -> i.getUsuario().getId().equals(usuarioId))
                .map(i -> i.getMentoria())
                // Aquí va el filtro — dime qué condición usar
                // .filter(m -> m.getFecha().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(mentorias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        mentoriaUsuarioService.cancelarInscripcion(id);
        return ResponseEntity.noContent().build();
    }
}