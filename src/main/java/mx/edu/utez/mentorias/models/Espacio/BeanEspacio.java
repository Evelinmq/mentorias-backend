package mx.edu.utez.mentorias.models.Espacio;

import jakarta.persistence.*;
import mx.edu.utez.mentorias.models.Edificio.BeanEdificio;

@Entity
@Table(name="espacio")
public class BeanEspacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    private BeanEdificio edificio;
}
