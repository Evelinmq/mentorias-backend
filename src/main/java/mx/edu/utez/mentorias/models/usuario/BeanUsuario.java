package mx.edu.utez.mentorias.models.usuario;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class BeanUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasena;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] foto;

    @ManyToOne
    @JoinColumn(name = "estado_usuario_id")
    private BeanEstadoUsuario estado;
}