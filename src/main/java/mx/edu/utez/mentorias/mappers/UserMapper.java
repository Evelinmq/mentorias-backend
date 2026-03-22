package mx.edu.utez.mentorias.mappers;

import mx.edu.utez.mentorias.contollers.user.dto.CreateUserDTO;
import mx.edu.utez.mentorias.contollers.user.dto.UserForClientDTO;
import mx.edu.utez.mentorias.models.usuario.BeanUsuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

// Función para convertir una entidad de user en userForClientDTO
    public UserForClientDTO userToUserDto(BeanUsuario user) {

        return new UserForClientDTO(
            user.getId(),
            user.getNombre(),
            user.getApellidos(),
            user.getCorreo(),
            user.getEstado(),
            user.getCarrera()
        );
    }

    // Transforma un listado de la entidadd BeanUSer en una lista de userForClientDTO
    public List<UserForClientDTO> usersToUserDtos(List<BeanUsuario> users) {

        List<UserForClientDTO> usersDtos = new ArrayList<>();

        for (BeanUsuario user : users) {
            usersDtos.add(userToUserDto(user));
        }
        return usersDtos;
    }

    public BeanUsuario createUserToBean(CreateUserDTO payload) {
        BeanUsuario newUser = new BeanUsuario();

        newUser.setNombre(payload.getNombres());
        newUser.setApellidos(payload.getApellidos());
        newUser.setCorreo(payload.getEmail());
        newUser.setContrasena(payload.getPassword());

        return newUser;
    }
}
