package mx.edu.utez.mentorias.contollers;

import mx.edu.utez.mentorias.models.usuario.BeanUsuario;
import mx.edu.utez.mentorias.services.Usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<BeanUsuario>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeanUsuario> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<BeanUsuario> crear(@RequestBody BeanUsuario usuario) {
        return new ResponseEntity<>(usuarioService.guardar(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeanUsuario> editar(@PathVariable Long id, @RequestBody BeanUsuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizar(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}