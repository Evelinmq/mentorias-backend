package mx.edu.utez.mentorias.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MentoriaMovilDTO {
    private Long id;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private Integer cupo;
    private String espacio;
    private String materia;
    private String mentor;
    private String email;


}
