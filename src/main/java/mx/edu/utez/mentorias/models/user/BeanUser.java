package mx.edu.utez.mentorias.models.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class BeanUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String email;
    private String password;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "user_status_id")
    private BeanUserStatus status;
}