package com.dairy.milk_tracking.services;

import com.dairy.milk_tracking.models.Processor;
import com.dairy.milk_tracking.repositories.ProcessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessorService {

    @Autowired
    private ProcessorRepository processorRepository;

    public List<Processor> getAllProcessors() {
        return processorRepository.findAll();
    }

    public Optional<Processor> getProcessorById(Long id) {
        return processorRepository.findById(id);
    }

    public Processor saveProcessor(Processor processor) {
        return processorRepository.save(processor);
    }

    public void deleteProcessor(Long id) {
        processorRepository.deleteById(id);
    }
}
