package mx.edu.utez.mentorias.models.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.mentorias.models.Carrera.BeanCarrera;
import mx.edu.utez.mentorias.models.EstadoUsuario.BeanEstadoUsuario;
import mx.edu.utez.mentorias.models.Rol.BeanRol;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class BeanUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String correo;
    @JsonIgnore
    private String contrasena;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] foto;

    @ManyToOne
    @JoinColumn(name = "estado_usuario_id")
    private BeanEstadoUsuario estado;

    @ManyToOne
    @JoinColumn(name = "carrera_usuario")
    private BeanCarrera carrera;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private List<BeanRol> roles;


}