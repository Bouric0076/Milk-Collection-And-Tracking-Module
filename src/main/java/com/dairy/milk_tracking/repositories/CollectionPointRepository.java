package com.dairy.milk_tracking.repositories;

import com.dairy.milk_tracking.models.CollectionPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionPointRepository extends JpaRepository<CollectionPoint, Long> {

    // Search by name
    CollectionPoint findByName(String name);

    // List all points in a certain area
    List<CollectionPoint> findByLocation(String location);

    // Optional: search case-insensitive
    List<CollectionPoint> findByLocationIgnoreCase(String location);
}
