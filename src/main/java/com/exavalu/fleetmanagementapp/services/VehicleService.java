package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.Vehicle;
import com.exavalu.fleetmanagementapp.repositories.VehicleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
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
    
    public List<Vehicle> getAllVehiclesWithUser(Integer id) {
    	return vehicleRepository.findAllVehiclesWithUser(id);
    }
    
    public List<Vehicle> getAllVehiclesWithGroup(String group) {
    	return vehicleRepository.findAllVehiclesByGroup(group);
    }

    public Optional<Vehicle> getVehicleById(Integer id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
    
    // returns a hashmap for vehicle status report on dashboard
    public String getVehicleReportJsonData() throws JsonProcessingException {
    	Hashtable<String, Integer> data = new Hashtable<>();
    	ArrayList<String> vehicleStatuses = new ArrayList<>();
    	vehicleStatuses.add("Active");
    	vehicleStatuses.add("Out of Service");
    	vehicleStatuses.add("In Shop");
    	vehicleStatuses.add("Unknown");
    	for (String status : vehicleStatuses) {
    		data.put(status, vehicleRepository.findAllVehiclesByStatus(status).size());
    	}
    	System.out.println(data.toString());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonData = mapper.writeValueAsString(data);
    	System.out.println(jsonData);
    	return jsonData;
    }
    // get all vehicles corresponding to the user id
    // what if we changed it so that it took in an argument of a list, and several flags for filters...
    // that would be faster
    
    public String getVehicleReportJsonData(List<Vehicle> vehicles) throws JsonProcessingException {
    	Hashtable<String, Integer> data = new Hashtable<>();
    	ArrayList<String> vehicleStatuses = new ArrayList<>();
    	vehicleStatuses.add("Active");
    	vehicleStatuses.add("Out of Service");
    	vehicleStatuses.add("In Shop");
    	vehicleStatuses.add("Unknown");
    	
    	data.put("Active", 0);
    	data.put("Out of Service", 0);
    	data.put("In Shop", 0);
    	data.put("Unknown", 0);
    	System.out.println(data.toString());
    	// counts the number of vehicles in each status
    	for (String status : vehicleStatuses) {
    		for (Vehicle vehicle : vehicles) {
    			if (vehicle.getStatus().equals(status)) {
    				data.put(status, data.get(status) + 1);
    			}
    		}
    	}
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonData = mapper.writeValueAsString(data);
    	System.out.println("JSON Vehicle Report Data:" + jsonData);
    	return jsonData;
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
