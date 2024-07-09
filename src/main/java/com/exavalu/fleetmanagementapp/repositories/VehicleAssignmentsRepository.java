package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.VehicleAssignments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleAssignmentsRepository extends JpaRepository<VehicleAssignments, Integer> {
    // You can define custom query methods here if needed
}
