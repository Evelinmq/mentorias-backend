package mx.edu.utez.mentorias.models.roles;

import jakarta.persistence.*;
import mx.edu.utez.mentorias.models.usuario.BeanUsuario;

@Entity
@Table(name = "aprendices")
public class BeanAprendiz {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private BeanUsuario usuario;
}

