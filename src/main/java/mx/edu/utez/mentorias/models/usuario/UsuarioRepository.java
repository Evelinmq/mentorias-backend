package mx.edu.utez.mentorias.models.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<BeanUsuario, Long> {

    @NativeQuery("SELECT * FROM " +
            "usuarios u JOIN usuario_rol ur" +
            "ON u.id = ur.usuarioID" +
            "WHERE NOMBRE = :nombre" +
            "AND ur.rolId = 'Mentor'")
    BeanUsuario findMentorByNombre(@Param
                                            ("nombre") String nombre
    );

}
