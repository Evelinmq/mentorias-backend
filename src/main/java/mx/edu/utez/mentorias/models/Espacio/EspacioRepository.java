package mx.edu.utez.mentorias.models.Espacio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacioRepository extends JpaRepository<BeanEspacio, Long> {
}
