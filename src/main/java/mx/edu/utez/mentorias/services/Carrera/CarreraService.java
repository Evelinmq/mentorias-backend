package mx.edu.utez.mentorias.services.Carrera;

import mx.edu.utez.mentorias.models.Carrera.BeanCarrera;
import mx.edu.utez.mentorias.models.Carrera.CarreraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraService {

    private CarreraRepository carreraRepository;

    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    public List<BeanCarrera> obtenerTodas() {
        return carreraRepository.findAll();
    }

    public BeanCarrera crearCarrera(BeanCarrera carrera) {
        return carreraRepository.save(carrera);
    }

    public BeanCarrera actualizar(Long id, BeanCarrera datosActualizados) {
        return carreraRepository.findById(id).map(carrera -> {
            carrera.setNombre(datosActualizados.getNombre());
            return carreraRepository.save(carrera);
        }).orElseThrow(() -> new RuntimeException("Carrera no encontrada con id: " + id));
    }


}
