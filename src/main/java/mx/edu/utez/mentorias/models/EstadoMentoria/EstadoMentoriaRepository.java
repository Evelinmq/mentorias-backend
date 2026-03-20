package mx.edu.utez.mentorias.models.EstadoMentoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoMentoriaRepository extends JpaRepository<BeanEstadoMentoria, Long> {
}
