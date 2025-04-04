package com.dairy.milk_tracking.repositories;

import com.dairy.milk_tracking.models.MilkCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MilkCollectionRepository extends JpaRepository<MilkCollection, Long> {
    
    // Find all milk collections for a specific farmer
    List<MilkCollection> findByFarmerId(Long farmerId);

    // Find all milk collections for a collection point
    List<MilkCollection> findByCollectionPointId(Long collectionPointId);
}
