package mx.edu.utez.mentorias;

import mx.edu.utez.mentorias.models.usuario.BeanUsuario;
import mx.edu.utez.mentorias.models.usuario.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class MentoriasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MentoriasApplication.class, args);
	}

	@Bean
	public CommandLineRunner encriptarContrasenasAntiguas(
			UsuarioRepository usuarioRepository,
			PasswordEncoder passwordEncoder) {

		return args -> {
			List<BeanUsuario> usuarios = usuarioRepository.findAll();
			boolean huboCambios = false;

			for (BeanUsuario usuario : usuarios) {
				String passActual = usuario.getContrasena();

				if (passActual != null && !passActual.startsWith("$2a$")) {
					String passEncriptada = passwordEncoder.encode(passActual);
					usuario.setContrasena(passEncriptada);
					usuarioRepository.save(usuario);
					huboCambios = true;
					System.out.println("Contraseña actualizada y encriptada para: " + usuario.getCorreo());
				}
			}

			if (huboCambios) {
				System.out.println("¡MIGRACIÓN EXITOSA! Las contraseñas en texto plano fueron encriptadas.");
			} else {
				System.out.println(" Todas las contraseñas ya están encriptadas correctamente.");
			}
		};
	}
}