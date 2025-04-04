package com.dairy.milk_tracking.services;

import com.dairy.milk_tracking.models.Collector;
import com.dairy.milk_tracking.repositories.CollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollectorService {

    @Autowired
    private CollectorRepository collectorRepository;

    /**
     * Get all collectors.
     * @return List of all collectors.
     */
    public List<Collector> getAllCollectors() {
        return collectorRepository.findAll();
    }

    /**
     * Get a collector by their ID.
     * @param id Collector's ID.
     * @return Collector object if found, else Optional.empty.
     */
    public Optional<Collector> getCollectorById(Long id) {
        return collectorRepository.findById(id);
    }

    /**
     * Save or update a collector.
     * @param collector Collector object to save or update.
     * @return Saved collector object.
     */
    public Collector saveCollector(Collector collector) {
        return collectorRepository.save(collector);
    }

    /**
     * Delete a collector by their ID.
     * @param id Collector's ID.
     */
    public void deleteCollector(Long id) {
        collectorRepository.deleteById(id);
    }

    /**
     * Find a collector by email (custom query method example).
     * @param email Collector's email.
     * @return Optional containing Collector if found.
     */
    public Optional<Collector> findByEmail(String email) {
        return collectorRepository.findByEmail(email);
    }
}
