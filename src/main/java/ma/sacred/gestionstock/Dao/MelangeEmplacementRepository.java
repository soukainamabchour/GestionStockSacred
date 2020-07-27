package ma.sacred.gestionstock.Dao;

import ma.sacred.gestionstock.Entities.MelangeEmplacement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MelangeEmplacementRepository extends JpaRepository<MelangeEmplacement, Long> {
    List<MelangeEmplacement> findByReference_Id(Long id);
}
