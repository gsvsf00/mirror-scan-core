package tech.bielsen.mirror_scan_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.bielsen.mirror_scan_api.model.ScanMirror;

import java.util.Optional;

@Repository
public interface ScanRepository extends MongoRepository <ScanMirror, String> {
    Optional<ScanMirror> findByTitle(String title);

    Optional<ScanMirror> findById(String id);
}
