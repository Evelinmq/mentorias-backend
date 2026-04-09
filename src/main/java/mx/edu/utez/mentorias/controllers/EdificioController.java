package mx.edu.utez.mentorias.controllers;

import mx.edu.utez.mentorias.models.Edificio.BeanEdificio;
import mx.edu.utez.mentorias.services.Edificio.EdificioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edificios")
@CrossOrigin(origins = "http://localhost:5173")
public class EdificioController {

    private final EdificioService edificioService;

    public EdificioController(EdificioService edificioService) {
        this.edificioService = edificioService;
    }

    // GET
    @GetMapping
    public ResponseEntity<List<BeanEdificio>> obtenerEdificios() {
        List<BeanEdificio> edificios = edificioService.listarTodos();
        return ResponseEntity.ok(edificios);
    }

    // GET por id
    @GetMapping("/{id}")
    public ResponseEntity<BeanEdificio> obtenerPorId(@PathVariable Long id) {
        BeanEdificio edificio = edificioService.buscarPorId(id);
        return ResponseEntity.ok(edificio);
    }
}
