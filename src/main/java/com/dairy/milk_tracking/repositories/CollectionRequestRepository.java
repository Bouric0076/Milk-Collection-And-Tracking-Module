package com.dairy.milk_tracking.repositories;

import com.dairy.milk_tracking.models.CollectionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CollectionRequestRepository extends JpaRepository<CollectionRequest, Long> {

    // Get all requests by status (PENDING, APPROVED, etc.)
    List<CollectionRequest> findByStatus(String status);

    // Get all requests made by a specific farmer
    List<CollectionRequest> findByFarmerId(Long farmerId);

    // Get all requests submitted to a specific collection point
    List<CollectionRequest> findByCollectionPointId(Long collectionPointId);
}
