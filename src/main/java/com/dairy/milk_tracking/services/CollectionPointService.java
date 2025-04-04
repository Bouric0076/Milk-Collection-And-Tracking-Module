package com.dairy.milk_tracking.services;

import com.dairy.milk_tracking.models.CollectionPoint;
import com.dairy.milk_tracking.repositories.CollectionPointRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionPointService {

    private final CollectionPointRepository collectionPointRepository;

    public CollectionPointService(CollectionPointRepository collectionPointRepository) {
        this.collectionPointRepository = collectionPointRepository;
    }

    // Get all collection points
    public List<CollectionPoint> getAllCollectionPoints() {
        return collectionPointRepository.findAll();
    }

    // Get a collection point by ID
    public Optional<CollectionPoint> getCollectionPointById(Long id) {
        return collectionPointRepository.findById(id);
    }

    // Get collection point by name
    public CollectionPoint getCollectionPointByName(String name) {
        return collectionPointRepository.findByName(name);
    }

    // Add a new collection point
    public CollectionPoint createCollectionPoint(CollectionPoint collectionPoint) {
        return collectionPointRepository.save(collectionPoint);
    }

    // Update an existing collection point
    public CollectionPoint updateCollectionPoint(Long id, CollectionPoint updatedPoint) {
        return collectionPointRepository.findById(id)
                .map(existingPoint -> {
                    existingPoint.setName(updatedPoint.getName());
                    existingPoint.setLocation(updatedPoint.getLocation());
                    existingPoint.setMilkPurchasePrice(updatedPoint.getMilkPurchasePrice());
                    return collectionPointRepository.save(existingPoint);
                })
                .orElseThrow(() -> new RuntimeException("Collection Point not found"));
    }

    // Delete a collection point
    public void deleteCollectionPoint(Long id) {
        collectionPointRepository.deleteById(id);
    }
}
