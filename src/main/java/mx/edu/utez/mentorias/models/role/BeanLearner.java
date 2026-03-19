package mx.edu.utez.mentorias.models.role;

import jakarta.persistence.*;
import mx.edu.utez.mentorias.models.user.BeanUser;

@Entity
@Table(name = "learners")
public class BeanLearner {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private BeanUser user;
}
