package mx.edu.utez.mentorias.models.UsuarioRol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRolRepository extends JpaRepository<BeanUsuarioRol, Long> {
}
