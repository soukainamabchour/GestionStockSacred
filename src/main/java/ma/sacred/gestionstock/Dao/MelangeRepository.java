package ma.sacred.gestionstock.Dao;

import ma.sacred.gestionstock.Entities.Melange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MelangeRepository extends JpaRepository<Melange, Long> {
    public Page<Melange> findByReferenceContains(String kw, Pageable pageable);
    public Page<Melange> findByReference_IdAndNLotContains(Long id, String kw, Pageable pageable);
    public Page<Melange> findByReference_Id(Long id, Pageable pageable);

}
