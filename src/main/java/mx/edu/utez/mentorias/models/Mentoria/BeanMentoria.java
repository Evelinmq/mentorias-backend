package mx.edu.utez.mentorias.models.Mentoria;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.mentorias.models.Espacio.BeanEspacio;
import mx.edu.utez.mentorias.models.EstadoMentoria.BeanEstadoMentoria;
import mx.edu.utez.mentorias.models.Materia.BeanMateria;
import mx.edu.utez.mentorias.models.Tema.BeanTema;
import mx.edu.utez.mentorias.models.usuario.BeanUsuario;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mentoria")
public class BeanMentoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "horaInicio")
    private LocalTime horaInicio;

    @Column(name = "horaFin")
    private LocalTime horaFin;

    private Integer cuatrimestre;
    private Integer cupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "espacioID")
    private BeanEspacio espacio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estadoID")
    private BeanEstadoMentoria estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentorID")
    private BeanUsuario mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materiaID")
    private BeanMateria materia;

    @OneToMany(mappedBy = "mentoria", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BeanTema> temas;
}
