package com.dairy.milk_tracking.repositories;


import com.dairy.milk_tracking.models.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    
    // Find farmer by phone number
    Farmer findByPhoneNumber(String phoneNumber);

    // Find all farmers at a specific collection point
    List<Farmer> findByPreferredCollectionPointId(Long collectionPointId);
}
