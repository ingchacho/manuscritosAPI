package manuscritos.repository;

import manuscritos.model.ManuscriptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ManuscriptRepository extends JpaRepository<ManuscriptEntity, Long> {
    Optional<ManuscriptEntity> finByContentHash(String contentHash);
    long countByClueFountTrue();
    long countByClueFountFalse();
}
