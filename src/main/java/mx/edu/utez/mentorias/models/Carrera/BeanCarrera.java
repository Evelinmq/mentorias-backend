package mx.edu.utez.mentorias.models.Carrera;


import jakarta.persistence.*;
import mx.edu.utez.mentorias.models.MateriaCarrera.BeanMateriaCarrera;

import java.util.List;

@Entity
@Table(name = "carrera")
public class BeanCarrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "carrera")
    private List<BeanMateriaCarrera> materiasAsignadas;
}
