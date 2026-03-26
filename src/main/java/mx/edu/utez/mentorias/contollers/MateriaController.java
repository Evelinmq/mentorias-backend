package mx.edu.utez.mentorias.contollers;

import mx.edu.utez.mentorias.models.Materia.BeanMateria;
import mx.edu.utez.mentorias.services.Materia.MateriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
@CrossOrigin(origins = "http://localhost:5173")
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public ResponseEntity<List<BeanMateria>> listar() {
        return ResponseEntity.ok(materiaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeanMateria> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(materiaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<BeanMateria> crear(@RequestBody BeanMateria materia) {
        return new ResponseEntity<>(materiaService.guardar(materia), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeanMateria> editar(@PathVariable Long id, @RequestBody BeanMateria materia) {
        return ResponseEntity.ok(materiaService.actualizar(id, materia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        materiaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
