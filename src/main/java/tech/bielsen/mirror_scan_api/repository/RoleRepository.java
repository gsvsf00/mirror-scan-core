package tech.bielsen.mirror_scan_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.bielsen.mirror_scan_api.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    // Find a role by authority
    Optional<Role> findByAuthority(String authority);
}
