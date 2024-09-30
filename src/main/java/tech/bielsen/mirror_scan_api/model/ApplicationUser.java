package tech.bielsen.mirror_scan_api.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Document
public class ApplicationUser implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String password;
    
    @JsonIgnore
    private ERole role;

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
        this.enabled = true;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == ERole.ROLE_ADMIN)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
                    );
        
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}
