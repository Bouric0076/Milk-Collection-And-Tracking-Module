package com.dairy.milk_tracking.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FarmerCollectionHistoryDTO {
    private Long requestId;
    private String status;
    private LocalDateTime requestTime;

    private double collectedAmount;
    private LocalDateTime collectionTime;

    private String farmerName;
    private String collectionPointName;
    private String collectionPointLocation;
    private double milkPurchasePrice;

    private String collectorName; // Nullable
    private double totalEarnings;
}
