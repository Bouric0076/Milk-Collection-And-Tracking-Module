package com.dairy.milk_tracking.controllers;

import com.dairy.milk_tracking.repositories.MilkCollectionRepository;
import com.dairy.milk_tracking.dto.CollectionRequestDTO;
import com.dairy.milk_tracking.dto.FarmerDTO;
import com.dairy.milk_tracking.dto.MilkCollectionDTO;
import com.dairy.milk_tracking.dto.CollectionPointDTO;
import com.dairy.milk_tracking.models.CollectionRequest;
import com.dairy.milk_tracking.models.MilkCollection;
import com.dairy.milk_tracking.services.CollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {

    private final CollectionService collectionService;
    private final MilkCollectionRepository milkCollectionRepository; // Declare the repository

    public CollectionController(CollectionService collectionService,
            MilkCollectionRepository milkCollectionRepository) {
        this.collectionService = collectionService;
        this.milkCollectionRepository = milkCollectionRepository; // Inject the repository
    }

    // 1. Farmer submits a collection request
    @PostMapping("/request")
    public ResponseEntity<CollectionRequestDTO> requestCollection(@RequestBody CollectionRequest request) {
        CollectionRequest savedRequest = collectionService.requestCollection(request);

        // Map CollectionRequest to CollectionRequestDTO
        CollectionRequestDTO requestDTO = new CollectionRequestDTO();
        requestDTO.setId(savedRequest.getId());
        requestDTO.setFarmerId(savedRequest.getFarmer().getId());
        requestDTO.setCollectionPointId(savedRequest.getCollectionPoint().getId());

        // Map additional fields
        requestDTO.setRequestedAmount(savedRequest.getRequestedAmount()); // Requested Amount
        requestDTO.setCollectedAmount(savedRequest.getCollectedAmount()); // Collected Amount (this might be null or 0
                                                                          // initially)
        requestDTO.setCollectionDate(savedRequest.getCollectionDate()); // Collection Date
        requestDTO.setStatus(savedRequest.getStatus()); // Collection Status (Pending, Approved, etc.)

        // Map FarmerDTO
        FarmerDTO farmerDTO = new FarmerDTO();
        farmerDTO.setId(savedRequest.getFarmer().getId());
        farmerDTO.setEmail(savedRequest.getFarmer().getEmail());
        farmerDTO.setUsername(savedRequest.getFarmer().getUsername());
        farmerDTO.setRole(savedRequest.getFarmer().getRole().name());
        farmerDTO.setPhoneNumber(savedRequest.getFarmer().getPhoneNumber());

        // Map CollectionPointDTO
        CollectionPointDTO collectionPointDTO = new CollectionPointDTO();
        collectionPointDTO.setId(savedRequest.getCollectionPoint().getId());
        collectionPointDTO.setName(savedRequest.getCollectionPoint().getName());
        collectionPointDTO.setLocation(savedRequest.getCollectionPoint().getLocation());
        collectionPointDTO
                .setMilkPurchasePrice(Double.parseDouble(savedRequest.getCollectionPoint().getMilkPurchasePrice()));

        requestDTO.setFarmer(farmerDTO);
        requestDTO.setCollectionPoint(collectionPointDTO);

        return ResponseEntity.ok(requestDTO);
    }

    // 2. View all pending collection requests (e.g. by Collectors)
    @GetMapping("/pending")
    public ResponseEntity<List<CollectionRequestDTO>> getPendingRequests() {
        List<CollectionRequest> collectionRequests = collectionService.getPendingRequests();

        // Map each CollectionRequest to CollectionRequestDTO
        List<CollectionRequestDTO> response = collectionRequests.stream()
                .map(request -> {
                    CollectionRequestDTO dto = new CollectionRequestDTO();
                    dto.setId(request.getId());
                    dto.setFarmerId(request.getFarmer().getId());
                    dto.setCollectionPointId(request.getCollectionPoint().getId());

                    // Map additional fields
                    dto.setRequestedAmount(request.getRequestedAmount()); // Requested Amount
                    dto.setCollectedAmount(request.getCollectedAmount()); // Collected Amount
                    dto.setCollectionDate(request.getCollectionDate()); // Collection Date
                    dto.setStatus(request.getStatus()); // Collection Status

                    // Map FarmerDTO
                    FarmerDTO farmerDTO = new FarmerDTO();
                    farmerDTO.setId(request.getFarmer().getId());
                    farmerDTO.setEmail(request.getFarmer().getEmail());
                    farmerDTO.setUsername(request.getFarmer().getUsername());
                    farmerDTO.setRole(request.getFarmer().getRole().name());
                    farmerDTO.setPhoneNumber(request.getFarmer().getPhoneNumber());

                    // Map CollectionPointDTO
                    CollectionPointDTO collectionPointDTO = new CollectionPointDTO();
                    collectionPointDTO.setId(request.getCollectionPoint().getId());
                    collectionPointDTO.setName(request.getCollectionPoint().getName());
                    collectionPointDTO.setLocation(request.getCollectionPoint().getLocation());
                    collectionPointDTO.setMilkPurchasePrice(
                            Double.parseDouble(request.getCollectionPoint().getMilkPurchasePrice()));

                    dto.setFarmer(farmerDTO);
                    dto.setCollectionPoint(collectionPointDTO);

                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // 3. Approve a pending request
    @PostMapping("/approve/{id}")
    public ResponseEntity<MilkCollection> approveCollection(
            @PathVariable Long id,
            @RequestParam double collectedAmount) {
        Optional<CollectionRequest> optionalRequest = collectionService.getCollectionRequestById(id);
        if (optionalRequest.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        CollectionRequest request = optionalRequest.get();
        MilkCollection milkCollection = collectionService.approveRequest(request, collectedAmount, null);
        return ResponseEntity.ok(milkCollection);
    }

    // 4. Reject a pending request
    @PostMapping("/reject/{id}")
    public ResponseEntity<String> rejectCollection(@PathVariable Long id) {
        Optional<CollectionRequest> optionalRequest = collectionService.getCollectionRequestById(id);
        if (optionalRequest.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        CollectionRequest request = optionalRequest.get();

        // Update the status to "Rejected"
        request.setStatus("Rejected");
        collectionService.save(request); // Assuming save() will persist the updated status

        return ResponseEntity.ok("Collection request has been rejected successfully.");
    }

    // 5. Get collection history for a specific farmer
    @GetMapping("/history/farmer/{farmerId}")
    public ResponseEntity<List<MilkCollection>> getCollectionHistoryForFarmer(@PathVariable Long farmerId) {
        List<MilkCollection> collections = collectionService.getCollectionsByFarmerId(farmerId);
        return ResponseEntity.ok(collections);
    }

    // 6. Get collection requests for a specific farmer with DTO
    @GetMapping("/requests/farmer/{farmerId}")
    public ResponseEntity<List<Map<String, Object>>> getFarmerRequests(@PathVariable Long farmerId) {
        List<CollectionRequest> requests = collectionService.getRequestsByFarmerId(farmerId);

        // Create a list to store the response that will be used to create the table
        List<Map<String, Object>> response = requests.stream()
                .map(request -> {
                    Map<String, Object> row = new HashMap<>();

                    // Extracting relevant fields for the table
                    row.put("Date", request.getCollectionDate());
                    row.put("Collection Point", request.getCollectionPoint().getName());
                    row.put("Location", request.getCollectionPoint().getLocation());

                    double litres = request.getRequestedAmount(); // Requested amount of milk
                    row.put("Requested Litres", litres);

                    double collected = request.getCollectedAmount(); // Collected amount of milk
                    row.put("Collected Litres", collected);

                    double pricePerLitre = Double.parseDouble(request.getCollectionPoint().getMilkPurchasePrice());
                    row.put("Price/Litre", pricePerLitre);

                    double total = collected * pricePerLitre; // Calculate total (litres * price)
                    row.put("Total (Ksh)", total);

                    String status = request.getStatus(); // Status of the request
                    row.put("Status", status);

                    return row;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/collected")
    public List<MilkCollectionDTO> getCollectedMilk() {
        // Fetch all milk collections
        List<MilkCollection> collections = milkCollectionRepository.findAll();

        return collections.stream().map(collection -> {
            MilkCollectionDTO dto = new MilkCollectionDTO();

            dto.setId(collection.getId());
            dto.setCollectedAmount(collection.getCollectedAmount()); // Collected amount of milk

            // Ensure farmer and collection point are not null
            dto.setFarmerName(collection.getFarmer() != null ? collection.getFarmer().getUsername() : "Unknown");
            dto.setCollectionPointName(
                    collection.getCollectionPoint() != null ? collection.getCollectionPoint().getName() : "Unknown");

            // Set collection time (default to current time if null)
            dto.setCollectionTime(collection.getCollectionTime() != null
                    ? collection.getCollectionTime()
                    : new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());

            return dto;
        }).collect(Collectors.toList());
    }

}
