package com.dairy.milk_tracking.controllers;

import com.dairy.milk_tracking.models.Farmer;
import com.dairy.milk_tracking.services.FarmerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    // Get all farmers
    @GetMapping
    public ResponseEntity<List<Farmer>> getAllFarmers() {
        return ResponseEntity.ok(farmerService.getAllFarmers());
    }

    // Get farmer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Farmer> getFarmerById(@PathVariable Long id) {
        Optional<Farmer> farmer = farmerService.getFarmerById(id);
        return farmer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get farmer by phone
    @GetMapping("/phoneNumber/{phoneNumber}")
    public ResponseEntity<Farmer> getFarmerByEmail(@PathVariable String phoneNumber) {
        Optional<Farmer> farmer = farmerService.getFarmerByPhoneNumber(phoneNumber);
        return farmer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all farmers by collection point
    @GetMapping("/collection-point/{collectionPointId}")
    public ResponseEntity<List<Farmer>> getFarmersByCollectionPoint(@PathVariable Long collectionPointId) {
        List<Farmer> farmers = farmerService.getFarmersByCollectionPoint(collectionPointId);
        return ResponseEntity.ok(farmers);
    }

    // Create a new farmer
    @PostMapping
    public ResponseEntity<Farmer> createFarmer(@RequestBody Farmer farmer) {
        Farmer newFarmer = farmerService.createFarmer(farmer);
        return ResponseEntity.ok(newFarmer);
    }

    // Update a farmer
    @PutMapping("/{id}")
    public ResponseEntity<Farmer> updateFarmer(@PathVariable Long id, @RequestBody Farmer updatedFarmer) {
        try {
            Farmer updated = farmerService.updateFarmer(id, updatedFarmer);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a farmer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
        return ResponseEntity.noContent().build();
    }
}
