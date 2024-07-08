package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.IssuesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuesReportRepository extends JpaRepository<IssuesReport, Integer> {
    // You can define custom query methods here if needed
}
