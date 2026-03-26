package mx.edu.utez.mentorias.contollers.auth;

import mx.edu.utez.mentorias.contollers.user.dto.LoginDTO;
import mx.edu.utez.mentorias.contollers.user.dto.LoginResponseDTO;
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

        return ResponseEntity.ok("");
    }

}