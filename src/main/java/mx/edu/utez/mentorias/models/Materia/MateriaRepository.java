package mx.edu.utez.mentorias.models.Materia;

import mx.edu.utez.mentorias.dto.MateriaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<BeanMateria,Long> {

    @Query("SELECT new mx.edu.utez.mentorias.dto.MateriaDTO(m.id, m.nombre, m.cuatrimestre, c.id, c.nombre) " +
            "FROM BeanMateriaCarrera mc " +
            "JOIN mc.materia m " +
            "JOIN mc.carrera c")
    List<MateriaDTO> findAllMateriasWithCarrera();

}
