package com.dairy.milk_tracking.repositories;


import com.dairy.milk_tracking.models.CollectionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CollectionRequestRepository extends JpaRepository<CollectionRequest, Long> {
    List<CollectionRequest> findByStatus(String status);
}
