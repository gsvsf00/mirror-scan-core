package tech.bielsen.mirror_scan_api.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.bielsen.mirror_scan_api.exceptions.EmailOrUserAlreadyTakenException;
import tech.bielsen.mirror_scan_api.exceptions.EmailOrUserDoNotExistException;
import tech.bielsen.mirror_scan_api.model.ApplicationUser;
import tech.bielsen.mirror_scan_api.model.ERole;
import tech.bielsen.mirror_scan_api.model.Role;
import tech.bielsen.mirror_scan_api.repository.RoleRepository;
import tech.bielsen.mirror_scan_api.repository.UserRepository;
import tech.bielsen.mirror_scan_api.model.RegistrationObject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepository;

    public ApplicationUser getUserByEmailOrUsername(String identifier) throws EmailOrUserDoNotExistException {
        if (identifier.contains("@")) {
            return userRepo.findByEmail(identifier).orElseThrow(EmailOrUserDoNotExistException::new);
        } else {
            return userRepo.findByUsername(identifier).orElseThrow(EmailOrUserDoNotExistException::new);
        }
    }

    public ApplicationUser updateUser(ApplicationUser user) {
        return userRepo.save(user);
    }

    public ApplicationUser registerUser(RegistrationObject ro) {
        ApplicationUser user = new ApplicationUser();

        if (userRepo.existsByUsername(ro.getUsername()) || userRepo.existsByEmail(ro.getEmail())) {
            throw new EmailOrUserAlreadyTakenException();
        }

        user.setUsername(ro.getUsername());
        user.setEmail(ro.getEmail());
        user.setPassword(ro.getPassword());
        user.setCreated(LocalDateTime.now());

        Set<Role> roles = user.getRoles();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseGet(() -> {
            Role newRole = new Role();
            newRole.setName(ERole.ROLE_USER);
            return roleRepository.save(newRole);
        }));
        user.setRoles(roles);

        userRepo.save(user);
        return user;
    }

    public List<ApplicationUser> getAllUsers() {
        return userRepo.findAll();
    }
}
