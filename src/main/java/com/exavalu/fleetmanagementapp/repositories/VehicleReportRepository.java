package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.VehicleReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleReportRepository extends JpaRepository<VehicleReport, Integer> {
    // You can define custom query methods here if needed
}
