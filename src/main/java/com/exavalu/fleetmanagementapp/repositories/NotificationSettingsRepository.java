package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.NotificationSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationSettingsRepository extends JpaRepository<NotificationSettings, Integer> {
    // You can define custom query methods here if needed
}
