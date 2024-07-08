package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.Vehicle;
import com.exavalu.fleetmanagementapp.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
    
    public List<Vehicle> getAllArchivedVehicles() {
    	return vehicleRepository.findAllVehiclesArchived();
    }
    
    public List<Vehicle> getAllWatchedVehicles() {
    	return vehicleRepository.findAllVehiclesWithActiveWatcher();
    }
    
    public List<Vehicle> getAllUnwatchedVehicles() {
    	return vehicleRepository.findAllVehiclesWithNoWatcher();
    }

    public Optional<Vehicle> getVehicleById(Integer id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> updateVehicle(Integer id, Vehicle vehicleDetails) {
        return vehicleRepository.findById(id).map(vehicle -> {
            vehicle.setName(vehicleDetails.getName());
            vehicle.setYear(vehicleDetails.getYear());
            vehicle.setMake(vehicleDetails.getMake());
            vehicle.setModel(vehicleDetails.getModel());
            vehicle.setVin(vehicleDetails.getVin());
            vehicle.setStatus(vehicleDetails.getStatus());
            vehicle.setType(vehicleDetails.getType());
            vehicle.setGroup(vehicleDetails.getGroup());
            vehicle.setMeter(vehicleDetails.getMeter());
            vehicle.setLicensePlate(vehicleDetails.getLicensePlate());
            vehicle.setWatcher(vehicleDetails.getWatcher());
            vehicle.setUser(vehicleDetails.getUser());
            vehicle.setTeam(vehicleDetails.getTeam());
            return vehicleRepository.save(vehicle);
        });
    }

    public boolean deleteVehicle(Integer id) {
        return vehicleRepository.findById(id).map(vehicle -> {
            vehicleRepository.delete(vehicle);
            return true;
        }).orElse(false);
    }
}
