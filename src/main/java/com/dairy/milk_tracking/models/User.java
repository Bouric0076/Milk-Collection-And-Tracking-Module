package com.dairy.milk_tracking.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import com.dairy.milk_tracking.models.CollectionRequest; // Import the CollectionRequest class

import com.fasterxml.jackson.annotation.JsonBackReference; // Import this
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // FARMER, COLLECTOR, PROCESSOR, ADMIN

    // Additional fields (if needed)
    private String phoneNumber;

    @JsonManagedReference(value = "farmer-requests") // Add this annotation to avoid circular references when //
                                                     // serializing User
    @OneToMany(mappedBy = "farmer")
    private List<CollectionRequest> collectionRequests; // Assuming User has a list of CollectionRequest objects
}
