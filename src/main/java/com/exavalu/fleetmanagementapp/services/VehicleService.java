package com.exavalu.fleetmanagementapp.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exavalu.fleetmanagementapp.models.Vehicle;
import com.exavalu.fleetmanagementapp.repositories.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Integer id) {
        try {
            return vehicleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Vehicle not found"));
        } catch (NoSuchElementException e) {
            // Handle the exception, e.g., log it or rethrow it
            throw e;
        }
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
