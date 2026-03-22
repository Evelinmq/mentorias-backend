package mx.edu.utez.mentorias.contollers;

import mx.edu.utez.mentorias.models.Rol.BeanRol;
import mx.edu.utez.mentorias.services.Rol.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<List<BeanRol>> listar() {
        return ResponseEntity.ok(rolService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeanRol> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(rolService.buscarPorId(id));
    }
}
