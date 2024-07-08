package com.exavalu.fleetmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.exavalu.fleetmanagementapp.models.ContactReport;

@Repository
public interface ContactReportRepository extends JpaRepository<ContactReport, Integer> {
    // You can define custom query methods here if needed
}
