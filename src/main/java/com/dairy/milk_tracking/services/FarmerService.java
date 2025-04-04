package com.dairy.milk_tracking.services;

import com.dairy.milk_tracking.models.Farmer;
import com.dairy.milk_tracking.repositories.FarmerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerService {
    
    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    // Get all farmers
    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }

    // Get a farmer by ID
    public Optional<Farmer> getFarmerById(Long id) {
        return farmerRepository.findById(id);
    }

    // Get a farmer by email
    public Optional<Farmer> getFarmerByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(farmerRepository.findByPhoneNumber(phoneNumber));
    }

    // Get all farmers linked to a collection point
    public List<Farmer> getFarmersByCollectionPoint(Long collectionPointId) {
        return farmerRepository.findByPreferredCollectionPointId(collectionPointId);
    }

    // Add a new farmer
    public Farmer createFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    // Update a farmer
    public Farmer updateFarmer(Long id, Farmer updatedFarmer) {
        return farmerRepository.findById(id)
                .map(existingFarmer -> {
                    existingFarmer.setFirstName(updatedFarmer.getFirstName());
                    existingFarmer.setLastName(updatedFarmer.getLastName());
                    existingFarmer.setPhoneNumber(updatedFarmer.getPhoneNumber());
                    existingFarmer.setEmail(updatedFarmer.getEmail());
                    existingFarmer.setGeolocation(updatedFarmer.getGeolocation());
                    existingFarmer.setNumberOfCows(updatedFarmer.getNumberOfCows());
                    existingFarmer.setPreferredCollectionPoint(updatedFarmer.getPreferredCollectionPoint());
                    return farmerRepository.save(existingFarmer);
                })
                .orElseThrow(() -> new RuntimeException("Farmer not found"));
    }

    // Delete a farmer
    public void deleteFarmer(Long id) {
        farmerRepository.deleteById(id);
    }
}

