package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.ServiceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceReportRepository extends JpaRepository<ServiceReport, Integer> {
    // You can define custom query methods here if needed
}
