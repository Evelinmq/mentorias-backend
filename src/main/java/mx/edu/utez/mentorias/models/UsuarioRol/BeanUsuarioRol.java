package mx.edu.utez.mentorias.models.UsuarioRol;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import mx.edu.utez.mentorias.models.Rol.BeanRol;
import mx.edu.utez.mentorias.models.usuario.BeanUsuario;

@Entity
@Table(name="usuario_rol")
public class BeanUsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private BeanUsuario usuario;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private BeanRol rol;

}
