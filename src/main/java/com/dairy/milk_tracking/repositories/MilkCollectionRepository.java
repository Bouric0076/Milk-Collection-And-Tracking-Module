package com.dairy.milk_tracking.repositories;

import com.dairy.milk_tracking.models.MilkCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MilkCollectionRepository extends JpaRepository<MilkCollection, Long> {

    // All collections done by a specific farmer
    List<MilkCollection> findByFarmerId(Long farmerId);

    // All collections done by a specific collector
    List<MilkCollection> findByCollectorId(Long collectorId);

    // All collections processed by a specific processor
    List<MilkCollection> findByProcessorId(Long processorId);

    // All collections made at a specific collection point
    List<MilkCollection> findByCollectionPointId(Long collectionPointId);

    // Find all collections done by a specific farmer within a date range
    List<MilkCollection> findByFarmerIdAndCollectionTimeBetween(Long farmerId, LocalDateTime start, LocalDateTime end);

    // Find all collections done by a specific collector within a date range
    List<MilkCollection> findByCollectorIdAndCollectionTimeBetween(Long collectorId, LocalDateTime start, LocalDateTime end);

    // Find all collections done by a specific processor within a date range
    List<MilkCollection> findByProcessorIdAndCollectionTimeBetween(Long processorId, LocalDateTime start, LocalDateTime end);

    // Find collections within a specific date range (general)
    List<MilkCollection> findByCollectionTimeBetween(LocalDateTime start, LocalDateTime end);

    // Find collections made by a specific farmer and a specific collection point
    List<MilkCollection> findByFarmerIdAndCollectionPointId(Long farmerId, Long collectionPointId);

    
    
    // You can also add pagination support, for example:
    // Page<MilkCollection> findByFarmerId(Long farmerId, Pageable pageable);
}
