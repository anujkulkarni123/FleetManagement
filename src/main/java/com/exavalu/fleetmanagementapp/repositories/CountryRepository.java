package com.exavalu.fleetmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exavalu.fleetmanagementapp.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
	
 
}
