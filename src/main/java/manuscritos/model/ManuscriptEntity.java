package manuscritos.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "manuscripts", uniqueConstraints = {
        @UniqueConstraint(name = "uk_manuscript_hash", columnNames = "content_hash")
})

public class ManuscriptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="content", columnDefinition="TEXT")
    private String content;

    @Column(name="content_hash", length=64, nullable=false)
    private String contentHash;

    @Column(name="clue_found")
    private boolean clueFound;

    @Column(name="created_at")
    private Instant createdAt;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getContentHash() { return contentHash; }
    public void setContentHash(String contentHash) { this.contentHash = contentHash; }
    public boolean isClueFound() { return clueFound; }
    public void setClueFound(boolean clueFound) { this.clueFound = clueFound; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

}
