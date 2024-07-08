package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.FuelReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelReportRepository extends JpaRepository<FuelReport, Integer> {
    
}
