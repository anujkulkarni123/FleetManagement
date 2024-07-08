package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.ItemFailures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemFailuresRepository extends JpaRepository<ItemFailures, Integer> {
    // You can define custom query methods here if needed
}
