package mx.edu.utez.mentorias.models.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user_statuses")
public class BeanUserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}