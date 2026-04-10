package mx.edu.utez.mentorias.controllers.Materia;

import mx.edu.utez.mentorias.dto.MateriaDTO;
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

    // ESTE ES EL ÚNICO GET PARA LA TABLA
    @GetMapping
    public ResponseEntity<List<MateriaDTO>> listar() {
        return ResponseEntity.ok(materiaService.listarTodasConCarrera());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeanMateria> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(materiaService.buscarPorId(id));
    }

    // CAMBIAMOS A MateriaDTO para que acepte el carreraId del select
    @PostMapping
    public ResponseEntity<BeanMateria> crear(@RequestBody MateriaDTO materiaDTO) {
        return new ResponseEntity<>(materiaService.guardar(materiaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeanMateria> editar(@PathVariable Long id, @RequestBody MateriaDTO dto) {
        return ResponseEntity.ok(materiaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        materiaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}