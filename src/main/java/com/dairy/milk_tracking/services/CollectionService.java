package com.dairy.milk_tracking.services;

import com.dairy.milk_tracking.models.CollectionRequest;
import com.dairy.milk_tracking.models.MilkCollection;
import com.dairy.milk_tracking.models.User;
import com.dairy.milk_tracking.repositories.CollectionRequestRepository;
import com.dairy.milk_tracking.repositories.MilkCollectionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionService {

    private final CollectionRequestRepository collectionRequestRepository;
    private final MilkCollectionRepository milkCollectionRepository;

    public CollectionService(CollectionRequestRepository collectionRequestRepository,
                             MilkCollectionRepository milkCollectionRepository) {
        this.collectionRequestRepository = collectionRequestRepository;
        this.milkCollectionRepository = milkCollectionRepository;
    }

    // Create a new collection request
    public CollectionRequest requestCollection(CollectionRequest request) {
        request.setRequestTime(LocalDateTime.now());
        request.setStatus("PENDING");
        return collectionRequestRepository.save(request);
    }

    // Get all pending collection requests
    public List<CollectionRequest> getPendingRequests() {
        return collectionRequestRepository.findByStatus("PENDING");
    }

    // Approve collection request and create milk collection record
    public MilkCollection approveRequest(CollectionRequest request, double collectedAmount, User collector) {
        request.setStatus("APPROVED");
        collectionRequestRepository.save(request);

        MilkCollection collection = new MilkCollection();
        collection.setCollectedAmount(collectedAmount);
        collection.setCollectionTime(LocalDateTime.now());
        collection.setFarmer(request.getFarmer()); // Refers to the farmer who made the request
        collection.setCollectionPoint(request.getCollectionPoint());
        collection.setCollector(collector); // Collector performing the action

        return milkCollectionRepository.save(collection);
    }

    // Optionally reject a collection request
    public CollectionRequest rejectRequest(CollectionRequest request) {
        request.setStatus("REJECTED");
        return collectionRequestRepository.save(request);
    }

    public Optional<CollectionRequest> getCollectionRequestById(Long id) {
        return collectionRequestRepository.findById(id);
    }
    
}
