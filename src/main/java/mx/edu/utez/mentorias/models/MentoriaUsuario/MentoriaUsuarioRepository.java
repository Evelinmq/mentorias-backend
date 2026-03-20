package mx.edu.utez.mentorias.models.MentoriaUsuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentoriaUsuarioRepository extends JpaRepository<BeanMentoriaUsuario, Long> {
}
