package mx.edu.utez.mentorias.models.Materia;


import jakarta.persistence.*;
import mx.edu.utez.mentorias.models.MateriaCarrera.BeanMateriaCarrera;

import java.util.List;

@Entity
@Table(name = "materia")
public class BeanMateria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

}
