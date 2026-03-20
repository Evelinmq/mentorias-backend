package mx.edu.utez.mentorias.models.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<BeanRol, Long> {
}
