package mx.edu.utez.mentorias.models.EstadoUsuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoUsuarioRepository extends JpaRepository<BeanEstadoUsuario, Long> {
}
