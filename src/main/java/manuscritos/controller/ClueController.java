package manuscritos.controller;

import manuscritos.dto.StatsDto;
import manuscritos.model.ManuscriptRequest;
import manuscritos.service.ClueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class ClueController {
    private final ClueService service;

    public ClueController(ClueService service) {
        this.service = service;
    }

    @PostMapping("/clue")
    public ResponseEntity<Void> clue(@Valid @RequestBody ManuscriptRequest req) {
        boolean found = service.analyzeAndStore(req);
        if (found) return ResponseEntity.ok().build();
        else return ResponseEntity.status(403).build();
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsDto> stats() {
        long found = service.countClueFound();
        long notFound = service.countNoClue();
        double ratio = (found + notFound) == 0 ? 0.0 : (double) found / (found + notFound);
        return ResponseEntity.ok(new StatsDto(found, notFound, ratio));
    }
}

