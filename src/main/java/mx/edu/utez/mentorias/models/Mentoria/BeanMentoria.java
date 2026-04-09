package mx.edu.utez.mentorias.models.Mentoria;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.mentorias.models.Espacio.BeanEspacio;
import mx.edu.utez.mentorias.models.EstadoMentoria.BeanEstadoMentoria;
import mx.edu.utez.mentorias.models.Materia.BeanMateria;
import mx.edu.utez.mentorias.models.MentoriaUsuario.BeanMentoriaUsuario;
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
    private Integer cuatrimestre;
    private Integer cupo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horaInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horaFin;

    @ManyToOne
    @JoinColumn(name = "espacio_id")
    private BeanEspacio espacio;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private BeanEstadoMentoria estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mentor_id")
    private BeanUsuario mentor;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private BeanMateria materia;

    @OneToMany(mappedBy = "mentoria")
    @JsonManagedReference(value = "mentoria-temas")
    private List<BeanTema> temas;

    @OneToMany(mappedBy = "mentoria", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "mentoria-alumnos")
    private List<BeanMentoriaUsuario> alumnos;
}
