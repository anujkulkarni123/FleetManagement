package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.Vehicle;
import com.exavalu.fleetmanagementapp.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    
    // main vehicle page
    @GetMapping
    public String getAll(Model model) {
    	// need to give it access to our list of vehicles
	 	List<Vehicle> vehicles = vehicleService.getAllVehicles();
	 	model.addAttribute("vehicles", vehicles);

    	return "vehicles";
    }
    
    @GetMapping("/assigned")
    public String getAssigned(Model model) {
    	List<Vehicle> assignedVehicles = vehicleService.getAllWatchedVehicles();
    	model.addAttribute("vehicles", assignedVehicles);
    	return "assigned";
    }
    
    @GetMapping("/unassigned")
    public String getUnassigned(Model model) {
    	List<Vehicle> unassignedVehicles = vehicleService.getAllUnwatchedVehicles();
    	model.addAttribute("vehicles", unassignedVehicles);
    	return "unassigned";
    }
    
    @GetMapping("/archived")
    public String getArchived(Model model) {
    	List<Vehicle> archivedVehicles = vehicleService.getAllArchivedVehicles();
    	model.addAttribute("vehicles", archivedVehicles);
    	return "archived";
    }
    
    // Get Vehicle by ID
    @GetMapping("/{id}")
    // we also need to access the corresponding users to assign stuff
    public String getVehicleById(@PathVariable Integer id, Model model) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        if (vehicle.isPresent()) {
            model.addAttribute("vehicle", vehicle.get());
            return "vehicle";
        } else {
            return "vehicleNotFound"; // or handle this case appropriately
        }
    }

    // Create new Vehicle
    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    // Update existing Vehicle
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicleDetails) {
        return vehicleService.updateVehicle(id, vehicleDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Vehicle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        if (vehicleService.deleteVehicle(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
