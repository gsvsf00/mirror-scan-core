package tech.bielsen.mirror_scan_api.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.bielsen.mirror_scan_api.model.ScanMirror;
import tech.bielsen.mirror_scan_api.repository.ScanRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ScanService {

    private final ScanRepository scanRepository;

    // Save a ScanMirror object
    public ScanMirror saveScanMirror(ScanMirror scanMirror) {
        return scanRepository.save(scanMirror);  // Use instance, not class name
    }

    // Find a ScanMirror by ID
    public Optional<ScanMirror> getScanMirrorById(String id) {
        return scanRepository.findById(id);  // Use instance, not class name
    }

    // Get all ScanMirror objects
    public List<ScanMirror> getAllScanMirrors() {
        return scanRepository.findAll();  // Use instance, not class name
    }

    // Update a ScanMirror by adding a favoriteUser entry (UserId, PageURL)
    public ScanMirror addFavoriteUser(String id, String userId, String pageUrl) {
        Optional<ScanMirror> scanOptional = scanRepository.findById(id);  // Use instance, not class name
        if (scanOptional.isPresent()) {
            ScanMirror scanMirror = scanOptional.get();
            scanMirror.getFavoriteUser().put(userId, pageUrl);  // Add entry to the Map
            return scanRepository.save(scanMirror);  // Save updated ScanMirror
        }
        return null;
    }

    // Delete a ScanMirror by ID
    public void deleteScan(String id) {
        scanRepository.deleteById(id);  // Use instance, not class name
    }
}