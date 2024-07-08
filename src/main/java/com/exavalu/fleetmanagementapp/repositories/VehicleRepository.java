package com.exavalu.fleetmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exavalu.fleetmanagementapp.models.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
