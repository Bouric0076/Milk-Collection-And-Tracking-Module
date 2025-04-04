package com.dairy.milk_tracking.repositories;

import com.dairy.milk_tracking.models.CollectionPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CollectionPointRepository extends JpaRepository<CollectionPoint, Long> {

    // Find a collection point by name
    CollectionPoint findByName(String name);

    // Find all collection points in a specific location
    List<CollectionPoint> findByLocation(String location);
}
