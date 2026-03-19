package mx.edu.utez.mentorias.models.Tema;


import jakarta.persistence.*;
import mx.edu.utez.mentorias.models.Mentoria.BeanMentoria;

@Entity
@Table (name = "tema")
public class BeanTema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Tema;

    @ManyToOne
    @JoinColumn(name = "mentoria")
    private BeanMentoria mentoria;
}
