package com.exavalu.fleetmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exavalu.fleetmanagementapp.models.Route;

public interface RouteRepository extends JpaRepository<Route, Integer> {
}
