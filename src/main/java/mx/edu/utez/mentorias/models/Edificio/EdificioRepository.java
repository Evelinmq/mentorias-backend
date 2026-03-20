package mx.edu.utez.mentorias.models.Edificio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdificioRepository extends JpaRepository<BeanEdificio, Long> {
}
