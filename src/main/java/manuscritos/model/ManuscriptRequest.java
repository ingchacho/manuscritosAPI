package manuscritos.model;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class ManuscriptRequest {
    @NotNull
    private List<String> manuscript;

    public List<String> getManuscript() { return manuscript; }
    public void setManuscript(List<String> manuscript) { this.manuscript = manuscript; }
}
