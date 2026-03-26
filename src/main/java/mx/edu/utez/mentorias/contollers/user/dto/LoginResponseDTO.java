package mx.edu.utez.mentorias.contollers.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String rol;
}