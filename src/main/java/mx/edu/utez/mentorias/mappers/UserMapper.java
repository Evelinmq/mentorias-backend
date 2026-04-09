package mx.edu.utez.mentorias.mappers;

import mx.edu.utez.mentorias.controllers.user.dto.CreateUserDTO;
import mx.edu.utez.mentorias.controllers.user.dto.UserForClientDTO;
import mx.edu.utez.mentorias.models.Carrera.BeanCarrera;
import mx.edu.utez.mentorias.models.Rol.BeanRol;
import mx.edu.utez.mentorias.models.usuario.BeanUsuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserForClientDTO userToUserDto(BeanUsuario user) {
        String nombresRoles = user.getRoles().stream()
                .map(BeanRol::getNombre)
                .collect(Collectors.joining(", "));

        List<Long> idsRoles = user.getRoles().stream()
                .map(BeanRol::getId)
                .collect(Collectors.toList());

        return new UserForClientDTO(
                user.getId(),
                user.getNombre(),
                user.getApellidoP(),
                user.getApellidoM(),
                user.getCorreo(),
                user.getEstado() != null ? user.getEstado().getNombre() : "N/A",
                user.getCarrera() != null ? user.getCarrera().getNombre() : "N/A",
                user.getCarrera() != null ? user.getCarrera().getId() : null,
                nombresRoles,
                idsRoles
        );
    }

    public List<UserForClientDTO> usersToUserDtos(List<BeanUsuario> users) {
        List<UserForClientDTO> usersDtos = new ArrayList<>();
        if (users == null) return usersDtos;

        for (BeanUsuario user : users) {
            usersDtos.add(userToUserDto(user));
        }
        return usersDtos;
    }

    public BeanUsuario createUserToBean(CreateUserDTO payload) {
        BeanUsuario newUser = new BeanUsuario();
        newUser.setNombre(payload.getNombre());
        newUser.setApellidoP(payload.getApellidoPaterno());
        newUser.setApellidoM(payload.getApellidoMaterno());
        newUser.setCorreo(payload.getEmail());
        newUser.setContrasena(payload.getPassword());



        if (payload.getCarreraId() != null) {
            BeanCarrera carrera = new BeanCarrera();
            carrera.setId(payload.getCarreraId());
            newUser.setCarrera(carrera);
        }

        if (payload.getRolesIds() != null) {
            List<BeanRol> roles = payload.getRolesIds().stream().map(id -> {
                BeanRol rol = new BeanRol();
                rol.setId(id);
                return rol;
            }).collect(Collectors.toList());

            newUser.setRoles(roles);
        }


        return newUser;
    }
}