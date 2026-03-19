package mx.edu.utez.mentorias.models.Mentoria;

import jakarta.persistence.*;
import mx.edu.utez.mentorias.models.Espacio.BeanEspacio;
import mx.edu.utez.mentorias.models.EstadoMentoria.BeanEstadoMentoria;
import mx.edu.utez.mentorias.models.Materia.BeanMateria;
import mx.edu.utez.mentorias.models.Tema.BeanTema;
import mx.edu.utez.mentorias.models.role.BeanMentor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "Espacio")
    private BeanEspacio espacio;

    @ManyToOne
    @JoinColumn(name = "Estado")
    private BeanEstadoMentoria estado;

    @ManyToOne
    @JoinColumn(name = "Mentor")
    private BeanMentor mentor;

    @ManyToOne
    @JoinColumn(name = "Materia")
    private BeanMateria materia;


    @OneToMany(mappedBy = "mentoria")
    private List<BeanTema> temas;
}
