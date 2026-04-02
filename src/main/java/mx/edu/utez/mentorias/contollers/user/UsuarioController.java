package mx.edu.utez.mentorias.contollers.user;

import mx.edu.utez.mentorias.contollers.user.dto.*;
import mx.edu.utez.mentorias.mappers.UserMapper;
import mx.edu.utez.mentorias.models.usuario.BeanUsuario;
import mx.edu.utez.mentorias.services.Usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UserMapper userMapper;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        this.userMapper = new UserMapper();
    }

    @GetMapping
    public ResponseEntity<List<UserForClientDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/estado/{nombreEstado}")
    public ResponseEntity<List<UserForClientDTO>> listarPorEstado(@PathVariable String nombreEstado) {
        return ResponseEntity.ok(usuarioService.listarPorEstado(nombreEstado));
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<UserForClientDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CreateUserDTO payload) {
        return new ResponseEntity<>(usuarioService.createUser(payload), HttpStatus.CREATED);
    }

    @PostMapping("/cambiar-estado")
    public ResponseEntity<?> cambiarEstado(@RequestBody Map<String, Long> payload) {
        usuarioService.cambiarEstado(payload.get("id"), payload.get("nuevoEstadoId"));
        return ResponseEntity.ok(Map.of("message", "Estado actualizado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeanUsuario> editar(@PathVariable Long id, @RequestBody CreateUserDTO payload) {
        BeanUsuario usuarioParaActualizar = userMapper.createUserToBean(payload);
        return ResponseEntity.ok(usuarioService.actualizar(id, usuarioParaActualizar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/nombre")
    public UserForClientDTO getUserByNombre(
            @RequestBody GetMentorByNameDTO getByNameDTO
            ){
        return usuarioService.getMentorByName(getByNameDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {

            LoginResponseDTO response = usuarioService.login(loginDTO);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}