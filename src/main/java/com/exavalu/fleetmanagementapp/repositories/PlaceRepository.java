package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {
    // You can define custom query methods here if needed
}
