package tech.bielsen.mirror_scan_api.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bielsen.mirror_scan_api.integration.core.Crawler;
import tech.bielsen.mirror_scan_api.integration.core.CrawlerPage;
import tech.bielsen.mirror_scan_api.integration.model.ChapterData;
import tech.bielsen.mirror_scan_api.integration.model.EnumSite;
import tech.bielsen.mirror_scan_api.model.ScanMirror;
import tech.bielsen.mirror_scan_api.services.ScanService;
import tech.bielsen.mirror_scan_api.integration.model.ScrapedItem;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ScrapingController {
    @Autowired
    private ScanService scanService;

    // Save a new ScanMirror object
    @PostMapping("/save")
    public ScanMirror createScanMirror(@RequestBody ScanMirror scanMirror) {
        return scanService.saveScanMirror(scanMirror);
    }

    // Get a ScanMirror by ID
    @GetMapping("/{id}")
    public Optional<ScanMirror> getScanMirror(@PathVariable String id) {
        return scanService.getScanMirrorById(id);
    }

    // Get all ScanMirror objects
    @GetMapping("/all")
    public List<ScanMirror> getAllScanMirrors() {
        return scanService.getAllScanMirrors();
    }

    // Add favorite user to a ScanMirror
    @PutMapping("/{id}/add-favorite")
    public ScanMirror addFavoriteUser(@PathVariable String id, @RequestParam String userId, @RequestParam String pageUrl) {
        return scanService.addFavoriteUser(id, userId, pageUrl);
    }

    // Delete a ScanMirror by ID
    @DeleteMapping("/{id}")
    public void deleteScanMirror(@PathVariable String id) {
        scanService.deleteScan(id);
    }

    @GetMapping("/scan")
    public ResponseEntity<List<ScrapedItem>> scanMockUrl() {
        Crawler c = new Crawler();
        List<ScrapedItem> scrapedItems = c.scrapeAsura("https://manhuaplus.com/?s=martial+peak&post_type=wp-manga");

        // Return the list of scraped items with an OK status
        if (scrapedItems != null) {
            return ResponseEntity.ok(scrapedItems);
        } else {
            // Return a not found status if scraping failed
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/scan/page/{url}")
    public ResponseEntity<List<ChapterData>> scanUrl(@PathVariable String url) {
        CrawlerPage c = new CrawlerPage();

        // You can dynamically choose which site to scrape based on some condition or default to ASURA
        EnumSite base = EnumSite.ASURA;

        String baseUrl = base.getBaseUrl();

        // Call the scraper, combining the base URL with the passed URL
        List<ChapterData> chapterData = c.scrapePage(base, baseUrl + url);

        // Return the list of scraped items with an OK status
        if (chapterData != null && !chapterData.isEmpty()) {
            return ResponseEntity.ok(chapterData);
        } else {
            // Return a not found status if scraping failed
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}