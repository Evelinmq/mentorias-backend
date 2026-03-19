package mx.edu.utez.mentorias.models.EstadoMentoria;


import jakarta.persistence.*;
import mx.edu.utez.mentorias.models.Mentoria.BeanMentoria;

import java.util.List;

@Entity
@Table(name = "estadoMentoria")
public class BeanEstadoMentoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

}
