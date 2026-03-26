package mx.edu.utez.mentorias;

import mx.edu.utez.mentorias.models.usuario.BeanUsuario;
import mx.edu.utez.mentorias.models.usuario.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MentoriasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MentoriasApplication.class, args);
	}

}