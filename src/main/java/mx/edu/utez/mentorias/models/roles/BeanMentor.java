package mx.edu.utez.mentorias.models.roles;

import jakarta.persistence.*;
import mx.edu.utez.mentorias.models.Mentoria.BeanMentoria;
import mx.edu.utez.mentorias.models.usuario.BeanUsuario;

import java.util.List;

@Entity
@Table(name = "mentores")
public class BeanMentor {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private BeanUsuario usuario;

    @OneToMany(mappedBy = "mentor")
    private List<BeanMentoria> mentorias;
}