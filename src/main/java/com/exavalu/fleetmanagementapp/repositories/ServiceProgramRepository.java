package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.ServiceProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProgramRepository extends JpaRepository<ServiceProgram, Integer> {
    // You can define custom query methods here if needed
}
