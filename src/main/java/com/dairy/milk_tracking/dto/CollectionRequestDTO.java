// CollectionRequestDTO.java
package com.dairy.milk_tracking.dto;

import java.time.LocalDateTime;

public class CollectionRequestDTO {
    private Long id;
    private Long farmerId;
    private Long collectionPointId;

    // Milk collection details
    private double requestedAmount;
    private double collectedAmount;
    private LocalDateTime collectionDate;
    private String status; // e.g., "PENDING", "APPROVED", "REJECTED"

    // Additional fields for nested objects
    private FarmerDTO farmer;
    private CollectionPointDTO collectionPoint;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public Long getCollectionPointId() {
        return collectionPointId;
    }

    public void setCollectionPointId(Long collectionPointId) {
        this.collectionPointId = collectionPointId;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public double getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(double collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    public LocalDateTime getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(LocalDateTime collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FarmerDTO getFarmer() {
        return farmer;
    }

    public void setFarmer(FarmerDTO farmer) {
        this.farmer = farmer;
    }

    public CollectionPointDTO getCollectionPoint() {
        return collectionPoint;
    }

    public void setCollectionPoint(CollectionPointDTO collectionPoint) {
        this.collectionPoint = collectionPoint;
    }
}
