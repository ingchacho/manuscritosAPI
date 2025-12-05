package manuscritos.service;

import manuscritos.model.ManuscriptEntity;
import manuscritos.model.ManuscriptRequest;
import manuscritos.repository.ManuscriptRepository;
import manuscritos.util.ClueUtils;
import manuscritos.util.ClueUtilsTwoLoops;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Optional;

@Service
public class ClueService {
    private final ManuscriptRepository repo;

    public ClueService(ManuscriptRepository repo) {
        this.repo = repo;
    }

    public static String sha256Hex(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] d = md.digest(s.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : d) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public boolean analyzeAndStore(ManuscriptRequest request) {
        String joined = String.join("\n", request.getManuscript());
        String hash = sha256Hex(joined);

        //Optional<ManuscriptEntity> existing = repo.finByContentHash(hash);
        Optional<ManuscriptEntity> existing = repo.findByContentHash(hash);
        if (existing.isPresent()) {
            return existing.get().isClueFound();
        }

        boolean clue = ClueUtils.containsArtifactClue(request.getManuscript());
        ManuscriptEntity e = new ManuscriptEntity();
        e.setContent(joined);
        e.setContentHash(hash);
        e.setClueFound(clue);
        e.setCreatedAt(Instant.now());
        repo.save(e);
        return clue;
    }

    public boolean analyzeAndStoreFast(ManuscriptRequest req) {
        boolean found = ClueUtilsTwoLoops.containsArtifactClueTwoLoops(req.getManuscript());
        saveFastResult(req, found);
        //repository.saveResult(req, found);
        return found;
    }

    private void saveFastResult(ManuscriptRequest req, boolean found) {
        String joined = String.join("\n", req.getManuscript());
        String hash = sha256Hex(joined);

        ManuscriptEntity e = new ManuscriptEntity();
        e.setContent(joined);
        e.setContentHash(hash);
        e.setClueFound(found);
        e.setCreatedAt(Instant.now());
        repo.save(e);
    }


    public long countClueFound() { return repo.countByClueFoundTrue(); }
    public long countNoClue() { return repo.countByClueFoundFalse(); }
/*
    public long countClueFound() { return repo.countByClueFountTrue(); }
    public long countNoClue() { return repo.countByClueFountFalse(); }
 */

}
