package tech.bielsen.mirror_scan_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bielsen.mirror_scan_api.exceptions.EmailOrUserAlreadyTakenException;
import tech.bielsen.mirror_scan_api.model.ApplicationUser;
import tech.bielsen.mirror_scan_api.model.RegistrationObject;
import tech.bielsen.mirror_scan_api.services.UserService;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler({EmailOrUserAlreadyTakenException.class})
    public ResponseEntity<String> handleEmailTaken() {
        return new ResponseEntity<String>("Email or username already taken", HttpStatus.CONFLICT);
    }

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationObject ro) {
        return userService.registerUser(ro);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test successful");
    }

    @PutMapping("/update/email")
    public ApplicationUser updateEmail(@RequestBody LinkedHashMap<String, String> body) {
        String username = body.get("username");
        String email = body.get("email");

        ApplicationUser user = userService.getUserByEmailOrUsername(username);
        user.setEmail(email);

        return userService.updateUser(user);
    }
}
