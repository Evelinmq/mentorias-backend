package mx.edu.utez.mentorias.models.Edificio;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.mentorias.models.Espacio.BeanEspacio;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "edificio")
public class BeanEdificio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @JsonManagedReference
    @OneToMany(mappedBy = "edificio")
    private List<BeanEspacio> espacios;
}
