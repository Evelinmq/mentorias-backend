package mx.edu.utez.mentorias.contollers;

import mx.edu.utez.mentorias.models.MateriaCarrera.BeanMateriaCarrera;
import mx.edu.utez.mentorias.services.MateriaCarrera.MateriaCarreraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materia-carrera")
@CrossOrigin(origins = "http://localhost:5173")
public class MateriaCarreraController {

    private final MateriaCarreraService materiaCarreraService;

    public MateriaCarreraController(MateriaCarreraService materiaCarreraService) {
        this.materiaCarreraService = materiaCarreraService;
    }

    @GetMapping
    public ResponseEntity<List<BeanMateriaCarrera>> listar() {
        return ResponseEntity.ok(materiaCarreraService.listarTodas());
    }

    // ASOCIAR
    @PostMapping
    public ResponseEntity<BeanMateriaCarrera> asociar(@RequestBody BeanMateriaCarrera union) {
        return new ResponseEntity<>(materiaCarreraService.guardar(union), HttpStatus.CREATED);
    }

    // DESVINCULAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desvincular(@PathVariable Long id) {
        materiaCarreraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}