package ma.sacred.gestionstock.Dao;

import ma.sacred.gestionstock.Entities.MelangeEmplacement;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MelangeEmplacementRepository extends JpaRepository<MelangeEmplacement, Long> {
    //Page<MelangeEmplacement> findByIdAndReference_IdAnd
}
