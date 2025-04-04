package com.dairy.milk_tracking.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class CollectionPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String milkPurchasePrice;

    @OneToMany(mappedBy = "preferredCollectionPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Farmer> farmers;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMilkPurchasePrice() {
        return milkPurchasePrice;
    }

    public void setMilkPurchasePrice(String milkPurchasePrice) {
        this.milkPurchasePrice = milkPurchasePrice;
    }

    public List<Farmer> getFarmers() {
        return farmers;
    }

    public void setFarmers(List<Farmer> farmers) {
        this.farmers = farmers;
    }
}
