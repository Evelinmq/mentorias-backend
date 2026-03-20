package mx.edu.utez.mentorias.models.Tema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<BeanTema, Long> {
}
