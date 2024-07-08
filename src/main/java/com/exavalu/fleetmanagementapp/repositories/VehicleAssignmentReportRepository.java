package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.VehicleAssignmentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleAssignmentReportRepository extends JpaRepository<VehicleAssignmentReport, Integer> {
    // You can define custom query methods here if needed
}
