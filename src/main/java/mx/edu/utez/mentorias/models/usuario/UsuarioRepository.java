package mx.edu.utez.mentorias.models.usuario;

import mx.edu.utez.mentorias.contollers.user.dto.UserForClientDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<BeanUsuario, Long> {
    Optional<BeanUsuario> findByCorreo(String correo);
    BeanUsuario findMentorByNombre(String nombre);

    /*@NativeQuery("SELECT * FROM " +
            "usuarios u JOIN usuario_rol ur" +
            "ON u.id = ur.usuarioID" +
            "WHERE NOMBRE = :nombre" +
            "AND ur.rolId = 'Mentor'")
    BeanUsuario findMentorByNombre(@Param("nombre") String nombre);
    Optional<BeanUsuario> findByCorreo(String correo);
*/

    @Query("SELECT DISTINCT u FROM BeanUsuario u " +
            "INNER JOIN FETCH u.estado e " +
            "LEFT JOIN FETCH u.carrera c " +
            "LEFT JOIN FETCH u.roles r " +
            "WHERE LOWER(e.nombre) = LOWER(:nombreEstado)")
    List<BeanUsuario> findAllByEstadoNombre(@Param("nombreEstado") String nombreEstado);

}
