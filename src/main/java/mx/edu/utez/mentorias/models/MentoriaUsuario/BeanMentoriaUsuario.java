package mx.edu.utez.mentorias.models.MentoriaUsuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.mentorias.models.Mentoria.BeanMentoria;
import mx.edu.utez.mentorias.models.usuario.BeanUsuario;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mentoria_usuario")
public class BeanMentoriaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private BeanUsuario usuario;

    @ManyToOne
    @JoinColumn(name = "mentoria_id")
    private BeanMentoria mentoria;

    private String rolDentro;
}
