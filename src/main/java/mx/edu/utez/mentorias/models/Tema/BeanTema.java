package mx.edu.utez.mentorias.models.Tema;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import mx.edu.utez.mentorias.models.Mentoria.BeanMentoria;

@Entity
@Table (name = "tema")
public class BeanTema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "mentoria_id")
    private BeanMentoria mentoria;
}
