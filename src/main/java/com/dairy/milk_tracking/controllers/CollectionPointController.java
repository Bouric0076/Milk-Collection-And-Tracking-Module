package com.dairy.milk_tracking.controllers;

import com.dairy.milk_tracking.models.CollectionPoint;
import com.dairy.milk_tracking.services.CollectionPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collection-points")
public class CollectionPointController {

    @Autowired
    private CollectionPointService collectionPointService;

    @GetMapping
    public ResponseEntity<List<CollectionPoint>> getAllCollectionPoints() {
        return ResponseEntity.ok(collectionPointService.getAllCollectionPoints());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionPoint> getCollectionPointById(@PathVariable Long id) {
        return collectionPointService.getCollectionPointById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CollectionPoint> createCollectionPoint(@RequestBody CollectionPoint collectionPoint) {
        CollectionPoint saved = collectionPointService.createCollectionPoint(collectionPoint);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionPoint> updateCollectionPoint(
            @PathVariable Long id,
            @RequestBody CollectionPoint collectionPoint) {
        try {
            CollectionPoint updated = collectionPointService.updateCollectionPoint(id, collectionPoint);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCollectionPoint(@PathVariable Long id) {
        try {
            collectionPointService.deleteCollectionPoint(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
