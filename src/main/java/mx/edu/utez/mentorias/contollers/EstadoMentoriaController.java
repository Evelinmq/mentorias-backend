package mx.edu.utez.mentorias.contollers;

import mx.edu.utez.mentorias.models.EstadoMentoria.BeanEstadoMentoria;
import mx.edu.utez.mentorias.services.EstadoMentoria.EstadoMentoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estados-mentorias")
public class EstadoMentoriaController {

    private final EstadoMentoriaService estadoMentoriaService;

    public EstadoMentoriaController(EstadoMentoriaService estadoMentoriaService) {
        this.estadoMentoriaService = estadoMentoriaService;
    }

    @GetMapping
    public ResponseEntity<List<BeanEstadoMentoria>> listar() {
        return ResponseEntity.ok(estadoMentoriaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeanEstadoMentoria> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(estadoMentoriaService.buscarPorId(id));
    }
}