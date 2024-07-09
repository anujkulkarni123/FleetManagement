package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.FleetService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FleetServiceRepository extends JpaRepository<FleetService, Integer> {
    // You can define custom query methods here if needed
}
