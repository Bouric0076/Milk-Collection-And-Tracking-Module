package com.dairy.milk_tracking.repositories;

import com.dairy.milk_tracking.models.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessorRepository extends JpaRepository<Processor, Long> {
    // Add custom query methods if needed
}
