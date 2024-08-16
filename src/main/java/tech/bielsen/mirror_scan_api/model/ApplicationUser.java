package tech.bielsen.mirror_scan_api.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class ApplicationUser {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    @Indexed(unique = true)
    private String email;
    private List<String> favoritesScan;
    private LocalDateTime created;
    private String role;
}
