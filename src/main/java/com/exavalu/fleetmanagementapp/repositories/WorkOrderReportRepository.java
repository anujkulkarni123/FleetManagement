package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.WorkOrderReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderReportRepository extends JpaRepository<WorkOrderReport, Integer> {
    // You can define custom query methods here if needed
}
