package manuscritos.repository;

import manuscritos.model.ManuscriptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ManuscriptRepository extends JpaRepository<ManuscriptEntity, Long> {
    Optional<ManuscriptEntity> findByContentHash(String contentHash);
    long countByClueFoundTrue();
    long countByClueFoundFalse();
    /*
    long countByClueFountTrue();
    long countByClueFountFalse();
     */


}
