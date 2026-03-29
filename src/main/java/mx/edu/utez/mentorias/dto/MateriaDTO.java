package mx.edu.utez.mentorias.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MateriaDTO {
    private Long id;
    private String nombre;
    private Integer cuatrimestre;
    private Long carreraId;
    private String nombreCarrera;
}