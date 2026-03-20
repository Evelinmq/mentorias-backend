package mx.edu.utez.mentorias.models.Materia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<BeanMateria,Long> {
}
