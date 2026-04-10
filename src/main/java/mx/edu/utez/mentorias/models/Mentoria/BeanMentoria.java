package mx.edu.utez.mentorias.models.Mentoria;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Integer cuatrimestre;
    private Integer cupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "espacio_id")
    private BeanEspacio espacio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id")
    private BeanEstadoMentoria estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private BeanUsuario mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id")
    private BeanMateria materia;

    @OneToMany(mappedBy = "mentoria")
    private List<BeanTema> temas;

    @OneToMany(mappedBy = "mentoria", fetch = FetchType.LAZY)
    private List<BeanMentoriaUsuario> alumnos;
}
