package mx.edu.utez.mentorias.models.MateriaCarrera;

import jakarta.persistence.*;
import mx.edu.utez.mentorias.models.Carrera.BeanCarrera;
import mx.edu.utez.mentorias.models.Materia.BeanMateria;

@Entity
@Table(name = "Materia_Carrera")
public class BeanMateriaCarrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Materia")
    private BeanMateria materia;

    @ManyToOne
    @JoinColumn(name = "Carrera")
    private BeanCarrera carrera;
}