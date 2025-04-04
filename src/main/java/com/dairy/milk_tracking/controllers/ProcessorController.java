package com.dairy.milk_tracking.controllers;

import com.dairy.milk_tracking.models.Processor;
import com.dairy.milk_tracking.services.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/processors")
public class ProcessorController {

    @Autowired
    private ProcessorService processorService;

    /**
     * Get all processors.
     * @return List of all processors.
     */
    @GetMapping
    public ResponseEntity<List<Processor>> getAllProcessors() {
        List<Processor> processors = processorService.getAllProcessors();
        return new ResponseEntity<>(processors, HttpStatus.OK);
    }

    /**
     * Get a processor by their ID.
     * @param id Processor's ID.
     * @return Processor object if found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Processor> getProcessorById(@PathVariable Long id) {
        Optional<Processor> processor = processorService.getProcessorById(id);
        return processor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Save or update a processor.
     * @param processor Processor object to save or update.
     * @return Saved processor object.
     */
    @PostMapping
    public ResponseEntity<Processor> saveProcessor(@RequestBody Processor processor) {
        Processor savedProcessor = processorService.saveProcessor(processor);
        return new ResponseEntity<>(savedProcessor, HttpStatus.CREATED);
    }

    /**
     * Delete a processor by their ID.
     * @param id Processor's ID.
     * @return Response indicating whether the deletion was successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcessor(@PathVariable Long id) {
        processorService.deleteProcessor(id);
        return ResponseEntity.noContent().build();
    }
}
