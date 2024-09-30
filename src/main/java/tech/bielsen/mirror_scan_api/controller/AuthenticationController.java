package tech.bielsen.mirror_scan_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;
import tech.bielsen.mirror_scan_api.exceptions.EmailOrUserAlreadyTakenException;
import tech.bielsen.mirror_scan_api.exceptions.EmailOrUserDoNotExistException;
import tech.bielsen.mirror_scan_api.model.ApplicationUser;
import tech.bielsen.mirror_scan_api.model.RegistrationObject;
import tech.bielsen.mirror_scan_api.model.dto.AuthenticationDTO;
import tech.bielsen.mirror_scan_api.services.UserService;
import tech.bielsen.mirror_scan_api.services.security.TokenService;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    TokenService tokenService;
    
    @PostMapping
    @ExceptionHandler({EmailOrUserAlreadyTakenException.class})
    public ResponseEntity<String> login(@RequestBody AuthenticationDTO request) {
        UsernamePasswordAuthenticationToken usernamePassword = 
                new UsernamePasswordAuthenticationToken(request.username(), request.password());
        
        var auth = this.authenticationManager.authenticate(usernamePassword);
        
        var token = tokenService.generateToken((ApplicationUser) auth.getPrincipal());
        
        return ResponseEntity.ok(token);
    }

    @ExceptionHandler({EmailOrUserAlreadyTakenException.class})
    public ResponseEntity<String> handleEmailTaken() {
        return new ResponseEntity<String>("Email or username already taken", HttpStatus.CONFLICT);
    }

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationObject ro) {
        return userService.registerUser(ro);
    }

    @ExceptionHandler({EmailOrUserDoNotExistException.class})
    public ResponseEntity<String> handleEmailOrUserDoNotExist() {
        return new ResponseEntity<String>("Email or username do not exist", HttpStatus.CONFLICT);
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
