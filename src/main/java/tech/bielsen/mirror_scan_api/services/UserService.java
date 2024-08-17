package tech.bielsen.mirror_scan_api.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.bielsen.mirror_scan_api.model.ApplicationUser;
import tech.bielsen.mirror_scan_api.repository.UserRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepo;

    public ApplicationUser createUser(ApplicationUser user) {
        return userRepo.save(user);
    }

    public List<ApplicationUser> getAllUsers() {
        return userRepo.findAll();
    }
}
