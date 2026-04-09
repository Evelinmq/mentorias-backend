package mx.edu.utez.mentorias.models.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Modifying
    @Transactional
    @Query("UPDATE BeanUsuario u SET u.estado.id = :estadoId WHERE u.id = :usuarioId")
    void updateEstado(@Param("usuarioId") Long usuarioId,
                      @Param("estadoId") Long estadoId);
}
