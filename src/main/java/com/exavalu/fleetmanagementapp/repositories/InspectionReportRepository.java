package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.InspectionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionReportRepository extends JpaRepository<InspectionReport, Integer> {
    // You can define custom query methods here if needed
}
