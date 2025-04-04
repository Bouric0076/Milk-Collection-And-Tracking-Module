package com.dairy.milk_tracking.repositories;

import com.dairy.milk_tracking.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    // Add custom query methods if needed
}
