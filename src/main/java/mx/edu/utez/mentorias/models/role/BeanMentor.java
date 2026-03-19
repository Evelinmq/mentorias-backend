package mx.edu.utez.mentorias.models.role;

import jakarta.persistence.*;
import mx.edu.utez.mentorias.models.Mentoria.BeanMentoria;
import mx.edu.utez.mentorias.models.user.BeanUser;

import java.util.List;

@Entity
@Table(name = "mentors")
public class BeanMentor {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private BeanUser user;

    @OneToMany(mappedBy = "mentor")
    private List<BeanMentoria> mentorias;
}
