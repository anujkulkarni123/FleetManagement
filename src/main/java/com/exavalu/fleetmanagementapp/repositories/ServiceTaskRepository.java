package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.ServiceTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTaskRepository extends JpaRepository<ServiceTask, Integer> {
    // You can define custom query methods here if needed
}
