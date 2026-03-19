package mx.edu.utez.mentorias.models.Edificio;


import jakarta.persistence.*;
import mx.edu.utez.mentorias.models.Espacio.BeanEspacio;

import java.util.List;

@Entity
@Table(name= "edificio")
public class BeanEdificio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany
    private List<BeanEspacio> Espacios;
}
