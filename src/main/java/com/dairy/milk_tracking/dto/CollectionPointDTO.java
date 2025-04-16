package com.dairy.milk_tracking.dto;

import java.util.List;

public class CollectionPointDTO {
    private Long id;
    private String name;
    private String location;
    private double milkPurchasePrice;
    private List<FarmerDTO> farmers;
    private List<CollectionRequestDTO> collectionRequests;

    // ✅ Added fields
    private String email;
    private String username;
    private String role;
    private String phoneNumber;

    // Constructors
    public CollectionPointDTO() {
    }

    public CollectionPointDTO(Long id, String name, String location, double milkPurchasePrice,
            List<FarmerDTO> farmers, List<CollectionRequestDTO> collectionRequests,
            String email, String username, String role, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.milkPurchasePrice = milkPurchasePrice;
        this.farmers = farmers;
        this.collectionRequests = collectionRequests;
        this.email = email;
        this.username = username;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

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

    public double getMilkPurchasePrice() {
        return milkPurchasePrice;
    }

    public void setMilkPurchasePrice(double milkPurchasePrice) {
        this.milkPurchasePrice = milkPurchasePrice;
    }

    public List<FarmerDTO> getFarmers() {
        return farmers;
    }

    public void setFarmers(List<FarmerDTO> farmers) {
        this.farmers = farmers;
    }

    public List<CollectionRequestDTO> getCollectionRequests() {
        return collectionRequests;
    }

    public void setCollectionRequests(List<CollectionRequestDTO> collectionRequests) {
        this.collectionRequests = collectionRequests;
    }

    // ✅ Getters and setters for the new fields
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
