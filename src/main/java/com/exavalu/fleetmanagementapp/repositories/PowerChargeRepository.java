package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.PowerCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerChargeRepository extends JpaRepository<PowerCharge, Integer> {
    // You can define custom query methods here if needed
}
