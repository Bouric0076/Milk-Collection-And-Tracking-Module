package com.dairy.milk_tracking.controllers;

import com.dairy.milk_tracking.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestParam String username, 
                                                        @RequestParam String password, 
                                                        @RequestParam String role) {
        try {
            // Call register method from AuthService
            String token = authService.registerUser(username, password, role);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("token", token));
        } catch (Exception e) {
            // Handle exceptions, such as already existing username, or missing role
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, 
                                                     @RequestParam String password) {
        try {
            // Call login method from AuthService
            String token = authService.authenticateUser(username, password);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            // Handle invalid credentials or other errors
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }
    }
}
