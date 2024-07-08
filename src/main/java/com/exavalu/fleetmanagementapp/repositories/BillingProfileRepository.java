package com.exavalu.fleetmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.exavalu.fleetmanagementapp.models.BillingProfile;

@Repository
public interface BillingProfileRepository extends JpaRepository<BillingProfile, Integer> {
    // You can define custom query methods here if needed
}
