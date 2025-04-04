package com.dairy.milk_tracking.controllers;

import com.dairy.milk_tracking.models.CollectionRequest;
import com.dairy.milk_tracking.models.MilkCollection;
import com.dairy.milk_tracking.services.CollectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping("/request")
    public CollectionRequest requestCollection(@RequestBody CollectionRequest request) {
        return collectionService.requestCollection(request);
    }

    @GetMapping("/pending")
    public List<CollectionRequest> getPendingRequests() {
        return collectionService.getPendingRequests();
    }

    @PostMapping("/approve/{id}")
    public MilkCollection approveCollection(@PathVariable Long id, @RequestParam double collectedAmount) {
        CollectionRequest request = collectionService.requestCollection(new CollectionRequest()); // Fetch request from DB
        return collectionService.approveRequest(request, collectedAmount);
    }
}
