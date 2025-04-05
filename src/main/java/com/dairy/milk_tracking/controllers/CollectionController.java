package com.dairy.milk_tracking.controllers;

import com.dairy.milk_tracking.models.CollectionRequest;
import com.dairy.milk_tracking.models.MilkCollection;
import com.dairy.milk_tracking.services.CollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    // 1. Farmer submits a collection request
    @PostMapping("/request")
    public ResponseEntity<CollectionRequest> requestCollection(@RequestBody CollectionRequest request) {
        CollectionRequest savedRequest = collectionService.requestCollection(request);
        return ResponseEntity.ok(savedRequest);
    }

    // 2. View all pending collection requests (e.g. by Collectors)
    @GetMapping("/pending")
    public ResponseEntity<List<CollectionRequest>> getPendingRequests() {
        return ResponseEntity.ok(collectionService.getPendingRequests());
    }

    // 3. Approve a pending request
    @PostMapping("/approve/{id}")
    public ResponseEntity<MilkCollection> approveCollection(
            @PathVariable Long id,
            @RequestParam double collectedAmount
    ) {
        Optional<CollectionRequest> optionalRequest = collectionService.getCollectionRequestById(id);
        if (optionalRequest.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        CollectionRequest request = optionalRequest.get();
        MilkCollection milkCollection = collectionService.approveRequest(request, collectedAmount, null);
        return ResponseEntity.ok(milkCollection);
    }
}
