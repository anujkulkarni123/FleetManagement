package com.exavalu.fleetmanagementapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exavalu.fleetmanagementapp.models.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	// future note: make sure to check that userId is also null or some default user
	
	@Query("SELECT v FROM Vehicle v WHERE v.watcher IS NOT null AND v.watcher != 'archived' ")
	List<Vehicle> findAllVehiclesWithActiveWatcher();
	
	@Query("SELECT v FROM Vehicle v WHERE v.watcher IS null AND v.watcher != 'archived' ")
	List<Vehicle> findAllVehiclesWithNoWatcher();
	
	@Query("SELECT v FROM Vehicle v WHERE v.watcher = 'archived' ")
	List<Vehicle> findAllVehiclesArchived();
	
	@Query("SELECT v FROM Vehicle v WHERE v.group = ?1")
	List<Vehicle> findAllVehiclesByGroup(String group);
	
	@Query("SELECT v FROM Vehicle v WHERE v.status = ?1")
	List<Vehicle> findAllVehiclesByStatus(String status);
	
	@Query("SELECT v FROM Vehicle v WHERE v.user.id = :userId")
	List<Vehicle> findAllVehiclesWithUser(@Param("userId") Integer userId);
}