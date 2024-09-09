package tech.bielsen.mirror_scan_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.bielsen.mirror_scan_api.model.ERole;
import tech.bielsen.mirror_scan_api.model.Role;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
