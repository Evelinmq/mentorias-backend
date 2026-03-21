package mx.edu.utez.mentorias.services.Carrera;

import mx.edu.utez.mentorias.models.Carrera.CarreraRepository;
import org.springframework.stereotype.Service;

@Service
public class CarreraService {

    private CarreraRepository carreraRepository;

    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

}
