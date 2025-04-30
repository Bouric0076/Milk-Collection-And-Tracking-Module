package com.dairy.milk_tracking.models;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    @JsonBackReference(value = "farmer-requests") // Unique name
    private User farmer;

    @ManyToOne
    @JoinColumn(name = "collection_point_id", nullable = false)
    @JsonBackReference(value = "point-requests") // Unique name
    private CollectionPoint collectionPoint;

    private double requestedAmount;

    private String status = "PENDING";

    private LocalDateTime requestTime = LocalDateTime.now();

    public LocalDateTime getCollectionDate() {
        return requestTime;
    }

    public double getCollectedAmount() {
        return requestedAmount;
    }

    public String getStatus() {
        return status;
    }
}
