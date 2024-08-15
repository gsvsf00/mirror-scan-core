package tech.bielsen.mirror_scan_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.bielsen.mirror_scan_api.model.user;

import java.util.Optional;

public interface UserRepository extends MongoRepository<user, String> {
    //Optional<user> finduserByEmail(String email);
}
