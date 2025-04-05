package com.dairy.milk_tracking.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MilkCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Collector user
    @ManyToOne(fetch = FetchType.LAZY)  // Optional: Lazy loading for performance
    @JoinColumn(name = "collector_id")
    private User collector;

    // Processor user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processor_id")
    private User processor;

    // Farmer user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_point_id", nullable = false)
    private CollectionPoint collectionPoint;

    private double collectedAmount;

    private LocalDateTime collectionTime;

    // Ensure collectionTime is set to the current time before persisting the entity
    @PrePersist
    public void prePersist() {
        if (collectionTime == null) {
            collectionTime = LocalDateTime.now();
        }
    }
}
