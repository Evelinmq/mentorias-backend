package mx.edu.utez.mentorias.models.Mentoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentoriaRepository extends JpaRepository<BeanMentoria, Long> {
}
