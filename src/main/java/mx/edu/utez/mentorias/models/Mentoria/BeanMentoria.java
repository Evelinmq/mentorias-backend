package mx.edu.utez.mentorias.models.Mentoria;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Integer cuatrimestre;
    private Integer cupo;

    @ManyToOne
    @JoinColumn(name = "espacio_id")
    private BeanEspacio espacio;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private BeanEstadoMentoria estado;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private BeanUsuario mentor;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private BeanMateria materia;


    @OneToMany(mappedBy = "mentoria")
    private List<BeanTema> temas;
}
