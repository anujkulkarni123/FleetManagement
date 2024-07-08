package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.PartsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartsReportRepository extends JpaRepository<PartsReport, Integer> {
    // You can define custom query methods here if needed
}
