package tech.bielsen.mirror_scan_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistrationObject {
    private final String username;
    private final String email;
    private final String password;
}
