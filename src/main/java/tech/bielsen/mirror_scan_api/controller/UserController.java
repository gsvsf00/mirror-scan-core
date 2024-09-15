package tech.bielsen.mirror_scan_api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bielsen.mirror_scan_api.integration.core.Crawller;
import tech.bielsen.mirror_scan_api.model.ApplicationUser;
import tech.bielsen.mirror_scan_api.services.UserService;
import tech.bielsen.mirror_scan_api.integration.model.ScrapedItem;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<ApplicationUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{identifier}")
    public ApplicationUser getUserByEmailOrUsername(@PathVariable String identifier) {
        return userService.getUserByEmailOrUsername(identifier);
    }

    @GetMapping("/scan")
    public ResponseEntity<List<ScrapedItem>> scanMockUrl() {
        Crawller c = new Crawller();
        List<ScrapedItem> scrapedItems = c.scrapeAsura("https://manhuaplus.com/?s=martial+peak&post_type=wp-manga");

        // Return the list of scraped items with an OK status
        if (scrapedItems != null) {
            return ResponseEntity.ok(scrapedItems);
        } else {
            // Return a not found status if scraping failed
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
