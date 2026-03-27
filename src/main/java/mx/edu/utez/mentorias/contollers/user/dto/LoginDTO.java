package mx.edu.utez.mentorias.contollers.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // Es vital tener constructor vacío para que Jackson (JSON) funcione
@AllArgsConstructor
public class LoginDTO {
    private String correo;
    private String password; // Cambiado de contrasena a password
}