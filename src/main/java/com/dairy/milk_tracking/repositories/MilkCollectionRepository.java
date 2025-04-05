package com.dairy.milk_tracking.repositories;

import com.dairy.milk_tracking.models.MilkCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
