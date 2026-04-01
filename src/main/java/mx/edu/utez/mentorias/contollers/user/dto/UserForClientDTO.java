package mx.edu.utez.mentorias.contollers.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.mentorias.models.Carrera.BeanCarrera;
import mx.edu.utez.mentorias.models.EstadoUsuario.BeanEstadoUsuario;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForClientDTO {
    private Long id;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String correo;
    private String nombreEstado;
    private String nombreCarrera;
    private Long idCarrera;
    private String nombreRol;
    private List<Long> idsRoles;
}