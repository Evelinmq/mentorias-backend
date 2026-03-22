package mx.edu.utez.mentorias.contollers.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.mentorias.models.Rol.BeanRol;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    private String nombres;
    private String apellidos;
    private String email;
    private String password;
    private BeanRol rol;
}
