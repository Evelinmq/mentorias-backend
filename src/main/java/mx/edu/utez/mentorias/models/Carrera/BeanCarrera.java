package mx.edu.utez.mentorias.models.Carrera;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.mentorias.models.MateriaCarrera.BeanMateriaCarrera;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "carrera")
public class BeanCarrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;


    @OneToMany(mappedBy = "carrera")
    @JsonIgnore
    private List<BeanMateriaCarrera> materiasAsignadas;


}
