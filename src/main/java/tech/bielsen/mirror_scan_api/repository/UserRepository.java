package tech.bielsen.mirror_scan_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.bielsen.mirror_scan_api.model.ApplicationUser;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<ApplicationUser, String> {


    Optional<ApplicationUser> findByUsername(String username);
    Optional<ApplicationUser> findByEmail(String identifier);
}
