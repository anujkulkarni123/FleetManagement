package com.exavalu.fleetmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.exavalu.fleetmanagementapp.models.Fault;

@Repository
public interface FaultRepository extends JpaRepository<Fault, Integer> {
}
