package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.ServiceReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceReminderRepository extends JpaRepository<ServiceReminder, Integer> {
    // You can define custom query methods here if needed
}
