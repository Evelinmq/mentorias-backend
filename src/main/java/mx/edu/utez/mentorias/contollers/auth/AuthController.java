package mx.edu.utez.mentorias.contollers.auth;

import mx.edu.utez.mentorias.contollers.user.dto.LoginDTO;
import mx.edu.utez.mentorias.contollers.user.dto.LoginResponseDTO;
import mx.edu.utez.mentorias.services.Usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /*
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        LoginResponseDTO response = usuarioService.login(loginDTO);
        if (response == null) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
        return ResponseEntity.ok(response);
    }
    */
}