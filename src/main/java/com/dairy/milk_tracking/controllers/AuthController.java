package com.dairy.milk_tracking.controllers;

import com.dairy.milk_tracking.models.Role;
import com.dairy.milk_tracking.models.User;
import com.dairy.milk_tracking.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest request) {
        User newUser = authService.registerUser(
            request.getUsername(),
            request.getEmail(),
            request.getPassword(),
            request.getRole(),
            request.getPhoneNumber()
        );
        return ResponseEntity.ok(newUser);
    }

    // Login and generate JWT token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        // Login and generate JWT token in the AuthService
        String jwtToken = authService.login(request.getEmail(), request.getPassword());

        if (jwtToken == null) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        // Find user by email
        User user = authService.findByEmail(request.getEmail()).orElseThrow();

        // Return response with token and user details
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + jwtToken)
                .body("Email: " + user.getEmail() + ", Role: " + user.getRole());
    }

    // Get user by email
    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = authService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get users by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable Role role) {
        return ResponseEntity.ok(authService.getUsersByRole(role));
    }
}

// Login Request DTO
class AuthRequest {
    private String email;
    private String password;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}

// User Registration Request DTO
class UserRegistrationRequest {
    private String username;
    private String email;
    private String password;
    private Role role;
    private String phoneNumber;

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
    public String getPhoneNumber() { return phoneNumber; }
}
