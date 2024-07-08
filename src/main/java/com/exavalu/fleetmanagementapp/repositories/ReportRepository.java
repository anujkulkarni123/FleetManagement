package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    // You can define custom query methods here if needed
}
