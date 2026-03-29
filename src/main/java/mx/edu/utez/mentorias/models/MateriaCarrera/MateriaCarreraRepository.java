package mx.edu.utez.mentorias.models.MateriaCarrera;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MateriaCarreraRepository extends JpaRepository<BeanMateriaCarrera, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM BeanMateriaCarrera mc WHERE mc.materia.id = :materiaId")
    void deleteByMateriaId(@Param("materiaId") Long materiaId);


    Optional<BeanMateriaCarrera> findByMateriaId(Long materiaId);

}
