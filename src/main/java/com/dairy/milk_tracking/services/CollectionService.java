package com.dairy.milk_tracking.services;

import com.dairy.milk_tracking.models.CollectionRequest;
import com.dairy.milk_tracking.models.MilkCollection;
import com.dairy.milk_tracking.repositories.CollectionRequestRepository;
import com.dairy.milk_tracking.repositories.MilkCollectionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CollectionService {

    private final CollectionRequestRepository collectionRequestRepository;
    private final MilkCollectionRepository milkCollectionRepository;

    public CollectionService(CollectionRequestRepository collectionRequestRepository, MilkCollectionRepository milkCollectionRepository) {
        this.collectionRequestRepository = collectionRequestRepository;
        this.milkCollectionRepository = milkCollectionRepository;
    }

    // Create a new collection request
    public CollectionRequest requestCollection(CollectionRequest request) {
        return collectionRequestRepository.save(request);
    }

    // Get all pending collection requests
    public List<CollectionRequest> getPendingRequests() {
        return collectionRequestRepository.findByStatus("PENDING");
    }

    // Approve collection request and create a milk collection record
    public MilkCollection approveRequest(CollectionRequest request, double collectedAmount) {
        request.setStatus("APPROVED");
        collectionRequestRepository.save(request);

        MilkCollection collection = new MilkCollection();
        collection.setFarmer(request.getFarmer());
        collection.setCollectionPoint(request.getCollectionPoint());
        collection.setCollectedAmount(collectedAmount);

        return milkCollectionRepository.save(collection);
    }
}
