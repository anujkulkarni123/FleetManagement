package com.exavalu.fleetmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.exavalu.fleetmanagementapp.models.ContactReminder;

@Repository
public interface ContactReminderRepository extends JpaRepository<ContactReminder, Integer> {
}
