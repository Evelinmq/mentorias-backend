package mx.edu.utez.mentorias.contollers.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String correo;
    private String contrasena;
}