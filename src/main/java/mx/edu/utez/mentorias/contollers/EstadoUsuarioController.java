package mx.edu.utez.mentorias.contollers;

import mx.edu.utez.mentorias.models.EstadoUsuario.BeanEstadoUsuario;
import mx.edu.utez.mentorias.services.EstadoUsuario.EstadoUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados-usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class EstadoUsuarioController {

    private final EstadoUsuarioService estadoUsuarioService;

    public EstadoUsuarioController(EstadoUsuarioService estadoUsuarioService) {
        this.estadoUsuarioService = estadoUsuarioService;
    }

    @GetMapping
    public ResponseEntity<List<BeanEstadoUsuario>> listar() {
        return ResponseEntity.ok(estadoUsuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeanEstadoUsuario> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(estadoUsuarioService.buscarPorId(id));
    }
}