package mx.edu.utez.mentorias.controllers.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String rol;
    private String token;
}