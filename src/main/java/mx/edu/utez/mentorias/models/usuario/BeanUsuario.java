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
@Table(name = "usuario")
public class BeanUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String correo;
    @JsonIgnore
    @Column(name = "contraseña")
    private String contrasena;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] foto;

    @ManyToOne
    @JoinColumn(name = "estadoID")
    private BeanEstadoUsuario estado;

    @ManyToOne
    @JoinColumn(name = "carreraID")
    private BeanCarrera carrera;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuariorol",
            joinColumns = @JoinColumn(name = "usuarioID"),
            inverseJoinColumns = @JoinColumn(name = "rolID")
    )
    @JsonIgnore
    private List<BeanRol> roles;


}