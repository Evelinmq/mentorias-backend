package mx.edu.utez.mentorias.contollers;

import mx.edu.utez.mentorias.models.MentoriaUsuario.BeanMentoriaUsuario;
import mx.edu.utez.mentorias.services.MentoriaUsuario.MentoriaUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        // INSCRIBIR
        @PostMapping
        public ResponseEntity<BeanMentoriaUsuario> inscribir(@RequestBody BeanMentoriaUsuario inscripcion) {
            return new ResponseEntity<>(mentoriaUsuarioService.inscribir(inscripcion), HttpStatus.CREATED);
        }

        // CANCELAR:
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> cancelar(@PathVariable Long id) {
            mentoriaUsuarioService.cancelarInscripcion(id);
            return ResponseEntity.noContent().build();
        }
    }

