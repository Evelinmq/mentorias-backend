package mx.edu.utez.mentorias.controllers.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // Es vital tener constructor vacío para que Jackson (JSON) funcione
@AllArgsConstructor
public class InscripcionDTO {
    private Long mentoriaId;
    private Long usuarioId;
    private String tema;
}
