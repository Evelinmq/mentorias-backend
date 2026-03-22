package mx.edu.utez.mentorias.contollers;

import mx.edu.utez.mentorias.models.EstadoUsuario.BeanEstadoUsuario;
import mx.edu.utez.mentorias.services.EstadoUsuario.EstadoUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estados-usuarios")
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