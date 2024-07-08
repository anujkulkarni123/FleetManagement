package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.InspectionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionScheduleRepository extends JpaRepository<InspectionSchedule, Integer> {
    // You can define custom query methods here if needed
}
