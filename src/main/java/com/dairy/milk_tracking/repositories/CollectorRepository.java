package com.dairy.milk_tracking.repositories;

import com.dairy.milk_tracking.models.Collector;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectorRepository extends JpaRepository<Collector, Long> {
    // Add custom query methods if needed, e.g.:
    // Optional<Collector> findByEmail(String email);
    Optional<Collector> findByEmail(String email);
}
