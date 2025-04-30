package com.dairy.milk_tracking.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

import com.dairy.milk_tracking.dto.CollectionPointDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @JsonManagedReference(value = "point-requests") // Add this line
    private List<CollectionRequest> collectionRequests;

}
