package mx.edu.utez.mentorias.models.EstadoUsuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EstadoUsuarioRepository extends JpaRepository<BeanEstadoUsuario, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE BeanUsuario u SET u.estado.id = :idEstado WHERE u.id = :idUsuario")
    void actualizarEstado(@Param("idUsuario") Long idUsuario, @Param("idEstado") Long idEstado);
}
