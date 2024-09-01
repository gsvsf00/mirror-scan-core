package tech.bielsen.mirror_scan_api.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.bielsen.mirror_scan_api.model.ApplicationUser;
import tech.bielsen.mirror_scan_api.repository.UserRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepo;

    public void createUser(ApplicationUser user) {
        userRepo.save(user);
    }

    public ApplicationUser getUserByEmailOrUsername(String identifier) throws UsernameNotFoundException {
        if (identifier.contains("@")) {
            return userRepo.findByEmail(identifier).orElseThrow(
                    () -> new UsernameNotFoundException("User not found")
            );
        } else {
            return userRepo.findByUsername(identifier).orElseThrow(
                    () -> new UsernameNotFoundException("User not found")
            );
        }
    }

    public List<ApplicationUser> getAllUsers() {
        return userRepo.findAll();
    }
}
