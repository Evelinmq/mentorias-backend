package mx.edu.utez.mentorias.models.roles;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import mx.edu.utez.mentorias.models.Mentoria.BeanMentoria;
import mx.edu.utez.mentorias.models.usuario.BeanUsuario;


@Entity 
@Table (name="mentores")
public class BeanMentor {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private BeanUsuario usuario;

    @OneToMany(mappedBy = "mentor")
    private List<BeanMentoria> mentorias;
}

