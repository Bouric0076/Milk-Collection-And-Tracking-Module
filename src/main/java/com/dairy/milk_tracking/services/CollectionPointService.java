package com.dairy.milk_tracking.services;

import com.dairy.milk_tracking.models.CollectionPoint;
import com.dairy.milk_tracking.repositories.CollectionPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionPointService {

    @Autowired
    private CollectionPointRepository collectionPointRepository;

    public List<CollectionPoint> getAllCollectionPoints() {
        return collectionPointRepository.findAll();
    }

    public Optional<CollectionPoint> getCollectionPointById(Long id) {
        return collectionPointRepository.findById(id);
    }

    public CollectionPoint createCollectionPoint(CollectionPoint collectionPoint) {
        return collectionPointRepository.save(collectionPoint);
    }

    public CollectionPoint updateCollectionPoint(Long id, CollectionPoint updatedCollectionPoint) {
        return collectionPointRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedCollectionPoint.getName());
                    existing.setLocation(updatedCollectionPoint.getLocation());
                    existing.setMilkPurchasePrice(updatedCollectionPoint.getMilkPurchasePrice());
                    return collectionPointRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("CollectionPoint not found with id " + id));
    }

    public void deleteCollectionPoint(Long id) {
        if (collectionPointRepository.existsById(id)) {
            collectionPointRepository.deleteById(id);
        } else {
            throw new RuntimeException("CollectionPoint not found with id " + id);
        }
    }
}
