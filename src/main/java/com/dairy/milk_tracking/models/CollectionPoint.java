package com.dairy.milk_tracking.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectionPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    private String milkPurchasePrice;

    @OneToMany(mappedBy = "collectionPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MilkCollection> milkCollections;

    @OneToMany(mappedBy = "collectionPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CollectionRequest> collectionRequests;
}
