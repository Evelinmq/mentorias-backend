package mx.edu.utez.mentorias.contollers;

import mx.edu.utez.mentorias.models.UsuarioRol.BeanUsuarioRol;
import mx.edu.utez.mentorias.services.UsuarioRol.UsuarioRolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios-roles")
public class UsuarioRolController {

    private final UsuarioRolService usuarioRolService;

    public UsuarioRolController(UsuarioRolService usuarioRolService) {
        this.usuarioRolService = usuarioRolService;
    }

    @GetMapping
    public ResponseEntity<List<BeanUsuarioRol>> listar() {
        return ResponseEntity.ok(usuarioRolService.listarTodas());
    }

    // ASIGNAR
    @PostMapping
    public ResponseEntity<BeanUsuarioRol> asignarRol(@RequestBody BeanUsuarioRol usuarioRol) {
        BeanUsuarioRol nuevaAsignacion = usuarioRolService.guardar(usuarioRol);
        return new ResponseEntity<>(nuevaAsignacion, HttpStatus.CREATED);
    }

    // REVOCAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> revocarRol(@PathVariable Long id) {
        usuarioRolService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}