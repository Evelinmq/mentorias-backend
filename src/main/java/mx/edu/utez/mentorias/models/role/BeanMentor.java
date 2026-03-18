package mx.edu.utez.mentorias.models.role;

import jakarta.persistence.*;
import mx.edu.utez.mentorias.models.user.BeanUser;

@Entity
@Table(name = "mentors")
public class BeanMentor {
    @Id
    private Long uniqueId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "unique_id")
    private BeanUser user;
}
