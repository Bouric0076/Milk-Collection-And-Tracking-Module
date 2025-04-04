package com.dairy.milk_tracking.controllers;

import com.dairy.milk_tracking.models.CollectionPoint;
import com.dairy.milk_tracking.services.CollectionPointService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/collection-points")
public class CollectionPointController {

    private final CollectionPointService collectionPointService;

    public CollectionPointController(CollectionPointService collectionPointService) {
        this.collectionPointService = collectionPointService;
    }

    // Get all collection points
    @GetMapping
    public ResponseEntity<List<CollectionPoint>> getAllCollectionPoints() {
        return ResponseEntity.ok(collectionPointService.getAllCollectionPoints());
    }

    // Get collection point by ID
    @GetMapping("/{id}")
    public ResponseEntity<CollectionPoint> getCollectionPointById(@PathVariable Long id) {
        Optional<CollectionPoint> collectionPoint = collectionPointService.getCollectionPointById(id);
        return collectionPoint.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get collection point by name
    @GetMapping("/name/{name}")
    public ResponseEntity<CollectionPoint> getCollectionPointByName(@PathVariable String name) {
        CollectionPoint collectionPoint = collectionPointService.getCollectionPointByName(name);
        return collectionPoint != null ? ResponseEntity.ok(collectionPoint) : ResponseEntity.notFound().build();
    }

    // Create a new collection point
    @PostMapping
    public ResponseEntity<CollectionPoint> createCollectionPoint(@RequestBody CollectionPoint collectionPoint) {
        CollectionPoint newPoint = collectionPointService.createCollectionPoint(collectionPoint);
        return ResponseEntity.ok(newPoint);
    }

    // Update a collection point
    @PutMapping("/{id}")
    public ResponseEntity<CollectionPoint> updateCollectionPoint(@PathVariable Long id, @RequestBody CollectionPoint updatedPoint) {
        try {
            CollectionPoint updated = collectionPointService.updateCollectionPoint(id, updatedPoint);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a collection point
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollectionPoint(@PathVariable Long id) {
        collectionPointService.deleteCollectionPoint(id);
        return ResponseEntity.noContent().build();
    }
}
