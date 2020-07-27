package ma.sacred.gestionstock.Dao;

import ma.sacred.gestionstock.Entities.MelangeRef;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MelangeRefRepository extends JpaRepository<MelangeRef, Long> {
    public Page<MelangeRef> findByReferenceContains(String keyword, Pageable pageable);
    //public Page<MelangeRef> findByReference(String ref);
    public Page<MelangeRef> findById(Long id, Pageable page);
}
