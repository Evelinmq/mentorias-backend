package mx.edu.utez.mentorias.models.user;

import jakarta.persistence.*;
import mx.edu.utez.mentorias.models.Carrera.BeanCarrera;

@Entity
@Table(name = "user")
public class BeanUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasena;
    private String foto;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private BeanUserStatus estado;

    @ManyToOne
    @JoinColumn(name="carrera_id")
    private BeanCarrera carrera;

    @OneToMany(mappedBy="usuario")
    private List<>roles;
}