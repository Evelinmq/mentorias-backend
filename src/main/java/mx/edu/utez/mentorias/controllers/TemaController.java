package mx.edu.utez.mentorias.controllers;

import mx.edu.utez.mentorias.models.Tema.BeanTema;
import mx.edu.utez.mentorias.services.Tema.TemaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temas")
@CrossOrigin(origins = "http://localhost:5173")
public class TemaController {

    private final TemaService temaService;

    public TemaController(TemaService temaService) {
        this.temaService = temaService;
    }

    @GetMapping
    public ResponseEntity<List<BeanTema>> listar() {
        return ResponseEntity.ok(temaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeanTema> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(temaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<BeanTema> crear(@RequestBody BeanTema tema) {
        return new ResponseEntity<>(temaService.guardar(tema), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        temaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
