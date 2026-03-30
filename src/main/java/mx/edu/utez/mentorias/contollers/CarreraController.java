package mx.edu.utez.mentorias.contollers;

import mx.edu.utez.mentorias.models.Carrera.BeanCarrera;
import mx.edu.utez.mentorias.services.Carrera.CarreraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carreras")
@CrossOrigin(origins = "http://localhost:5173")
public class CarreraController {

    private final CarreraService carreraService;

    public CarreraController(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @GetMapping
    public List<BeanCarrera> listarTodas() {
        return carreraService.obtenerTodas();
    }

    // AGREGAR
    @PostMapping
    public ResponseEntity<BeanCarrera> agregar(@RequestBody BeanCarrera carrera) {
        BeanCarrera nuevaCarrera = carreraService.crearCarrera(carrera);
        return ResponseEntity.ok(nuevaCarrera);
    }

    // EDITAR
    @PutMapping("/{id}")
    public ResponseEntity<BeanCarrera> editar(@PathVariable Long id, @RequestBody BeanCarrera carrera) {
        BeanCarrera actualizada = carreraService.actualizar(id, carrera);
        return ResponseEntity.ok(actualizada);
    }

}