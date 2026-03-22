package mx.edu.utez.mentorias.contollers.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.mentorias.models.Carrera.BeanCarrera;
import mx.edu.utez.mentorias.models.EstadoUsuario.BeanEstadoUsuario;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForClientDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String correo;
    private BeanEstadoUsuario estado;
    private BeanCarrera carrera;
}
