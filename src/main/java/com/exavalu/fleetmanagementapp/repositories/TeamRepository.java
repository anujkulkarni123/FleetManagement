package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    // You can define custom query methods here if needed
}
