package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.VehicleReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleReminderRepository extends JpaRepository<VehicleReminder, Integer> {
    // You can define custom query methods here if needed
}
