package tech.bielsen.mirror_scan_api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bielsen.mirror_scan_api.model.ApplicationUser;
import tech.bielsen.mirror_scan_api.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
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
}
