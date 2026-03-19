package mx.edu.utez.mentorias.models.usuario;

import jakarta.persistence.*;

@Entity
@Table(name = "estados_usuarios")
public class BeanEstadoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
}