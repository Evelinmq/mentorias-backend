package mx.edu.utez.mentorias.models.MateriaCarrera;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaCarreraRepository extends JpaRepository<BeanMateriaCarrera, Long> {
}
