package tech.bielsen.mirror_scan_api.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Document
public class ApplicationUser {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String password;

    @JsonIgnore
    @Indexed(unique = true)
    private String email;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    private String displayName;

    @JsonIgnore
    private List<String> favoritesScan;

    @JsonIgnore
    private boolean enabled;

    @JsonIgnore
    private Long verificationCode;

    private LocalDateTime created;

    public ApplicationUser() {
        this.enabled = false;
    }
}
