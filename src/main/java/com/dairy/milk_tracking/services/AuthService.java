package com.dairy.milk_tracking.services;

import com.dairy.milk_tracking.models.Role;
import com.dairy.milk_tracking.models.User;
import com.dairy.milk_tracking.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${jwt.secret}") // JWT secret key from properties
    private String jwtSecret;

    // Register a new user
    public User registerUser(String username, String email, String password, Role role, String phoneNumber) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already in use");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password); // Assuming password is already encoded
        user.setRole(role);
        user.setPhoneNumber(phoneNumber);

        return userRepository.save(user);
    }

    // Login user and generate JWT token
    public String login(String email, String password) {
        // Authenticate the user credentials using AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        // If authentication is successful, generate JWT token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Generate JWT token (you can customize the claims as needed)
        return generateToken(userDetails.getUsername(), userDetails.getAuthorities().toString());
    }

    // Generate JWT token
    private String generateToken(String username, String authorities) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Token valid for 1 day
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    // Find user by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Get users by role
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }
}
