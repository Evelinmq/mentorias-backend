package mx.edu.utez.mentorias.models.UsuarioRol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRolRepository extends JpaRepository<BeanUsuarioRol, Long> {

    // Busca los roles de un usuario por su ID
    List<BeanUsuarioRol> findByUsuario_Id(Long usuarioId);

}