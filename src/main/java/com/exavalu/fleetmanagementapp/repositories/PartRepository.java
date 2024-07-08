package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {
    // You can define custom query methods here if needed
}
