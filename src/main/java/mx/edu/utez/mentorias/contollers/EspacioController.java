package mx.edu.utez.mentorias.contollers;

import mx.edu.utez.mentorias.models.Espacio.BeanEspacio;
import mx.edu.utez.mentorias.services.Espacio.EspacioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/espacios")
public class EspacioController {

    private final EspacioService espacioService;

    public EspacioController(EspacioService espacioService) {
        this.espacioService = espacioService;
    }

    // GET
    @GetMapping
    public ResponseEntity<List<BeanEspacio>> listar() {
        return ResponseEntity.ok(espacioService.listarTodos());
    }

    // GET por id
    @GetMapping("/{id}")
    public ResponseEntity<BeanEspacio> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(espacioService.buscarPorId(id));
    }
}