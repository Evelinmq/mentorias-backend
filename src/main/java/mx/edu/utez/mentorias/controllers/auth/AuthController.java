package mx.edu.utez.mentorias.controllers.auth;

import mx.edu.utez.mentorias.controllers.user.dto.LoginDTO;
import mx.edu.utez.mentorias.controllers.user.dto.LoginResponseDTO;
import mx.edu.utez.mentorias.services.Usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin({"*"})
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            // 1. Llamamos al servicio (él es quien busca en la BD y compara la contraseña)
            LoginResponseDTO response = usuarioService.login(loginDTO);

            // 2. Si todo sale bien, regresamos el objeto que el servicio construyó
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // 3. Si el usuario no existe o la contraseña está mal, lanzamos el error
            return ResponseEntity.status(401).body("Credenciales incorrectas: " + e.getMessage());
        }
    }

}