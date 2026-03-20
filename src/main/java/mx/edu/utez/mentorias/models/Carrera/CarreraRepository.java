package mx.edu.utez.mentorias.models.Carrera;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreraRepository extends JpaRepository<BeanCarrera, Long> {
}
