package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.MeterHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterHistoryRepository extends JpaRepository<MeterHistory, Integer> {
    // You can define custom query methods here if needed
}
