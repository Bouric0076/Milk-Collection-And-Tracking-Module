package com.dairy.milk_tracking.controllers;

import com.dairy.milk_tracking.models.Collector;
import com.dairy.milk_tracking.services.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/collectors")
public class CollectorController {

    @Autowired
    private CollectorService collectorService;

    /**
     * Get all collectors.
     * @return List of all collectors.
     */
    @GetMapping
    public ResponseEntity<List<Collector>> getAllCollectors() {
        List<Collector> collectors = collectorService.getAllCollectors();
        return new ResponseEntity<>(collectors, HttpStatus.OK);
    }

    /**
     * Get a collector by their ID.
     * @param id Collector's ID.
     * @return Collector object if found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Collector> getCollectorById(@PathVariable Long id) {
        Optional<Collector> collector = collectorService.getCollectorById(id);
        return collector.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Save or update a collector.
     * @param collector Collector object to save or update.
     * @return Saved collector object.
     */
    @PostMapping
    public ResponseEntity<Collector> saveCollector(@RequestBody Collector collector) {
        Collector savedCollector = collectorService.saveCollector(collector);
        return new ResponseEntity<>(savedCollector, HttpStatus.CREATED);
    }

    /**
     * Delete a collector by their ID.
     * @param id Collector's ID.
     * @return Response indicating whether the deletion was successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollector(@PathVariable Long id) {
        collectorService.deleteCollector(id);
        return ResponseEntity.noContent().build();
    }
}
