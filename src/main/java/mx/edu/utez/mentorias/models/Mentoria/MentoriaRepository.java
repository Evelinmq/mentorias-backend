package mx.edu.utez.mentorias.models.Mentoria;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MentoriaRepository extends JpaRepository<BeanMentoria, Long> {

    // 🔥 ESTE ES EL IMPORTANTE
    @Override
    @EntityGraph(attributePaths = {
            "espacio",
            "estado",
            "mentor",
            "materia"
    })
    List<BeanMentoria> findAll();

    // Cupo
    @EntityGraph(attributePaths = {
            "espacio",
            "estado",
            "mentor",
            "materia"
    })
    List<BeanMentoria> findAllByCupoLessThan(int limite);

    // Rango de fechas
    @EntityGraph(attributePaths = {
            "espacio",
            "estado",
            "mentor",
            "materia"
    })
    List<BeanMentoria> findByFechaBetween(LocalDate inicio, LocalDate fin);

    // Una sola fecha
    @EntityGraph(attributePaths = {
            "espacio",
            "estado",
            "mentor",
            "materia"
    })
    List<BeanMentoria> findByFecha(LocalDate fecha);

    @EntityGraph(attributePaths = {
            "espacio",
            "estado",
            "mentor",
            "materia"
    })
    @Query("SELECT m FROM BeanMentoria m WHERE " +
            "(m.fecha BETWEEN :inicio AND :fin) AND " +
            "(:materiaId IS NULL OR m.materia.id = :materiaId) AND " +
            "(:mentorId IS NULL OR m.mentor.id = :mentorId)")
    List<BeanMentoria> filtrarDinamico(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin,
            @Param("materiaId") Long materiaId,
            @Param("mentorId") Long mentorId);
}