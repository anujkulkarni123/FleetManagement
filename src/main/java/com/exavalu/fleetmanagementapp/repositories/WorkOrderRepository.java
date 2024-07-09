package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Integer> {
    // You can define custom query methods here if needed
}
