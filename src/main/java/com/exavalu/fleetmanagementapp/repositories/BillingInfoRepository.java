package com.exavalu.fleetmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.exavalu.fleetmanagementapp.models.BillingInfo;

@Repository
public interface BillingInfoRepository extends JpaRepository<BillingInfo, Integer> {
    // You can define custom query methods here if needed
}
