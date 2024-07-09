package com.exavalu.fleetmanagementapp.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exavalu.fleetmanagementapp.models.User;
import com.exavalu.fleetmanagementapp.models.Vehicle;
import com.exavalu.fleetmanagementapp.models.VehicleAssignments;
import com.exavalu.fleetmanagementapp.services.UserService;
import com.exavalu.fleetmanagementapp.services.VehicleAssignmentsService;
import com.exavalu.fleetmanagementapp.services.VehicleService;

@Controller
@RequestMapping("/vehicle-assignments")
public class VehicleAssignmentsController {

    @Autowired
    private VehicleAssignmentsService vehicleAssignmentsService;
    
    @Autowired
    private VehicleService vehicleService;
    
    @Autowired
    private UserService userService;

    // Get all VehicleAssignments
    @GetMapping
    public String getAllVehicleAssignments(Model model) {
        List<VehicleAssignments> assignments = vehicleAssignmentsService.getAllVehicleAssignments();
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        List<User> drivers = userService.getAllUsers();  
        model.addAttribute("vehicleList", vehicles);
        model.addAttribute("driverList", drivers); 
        model.addAttribute("assignments", assignments);
        model.addAttribute("assignment", new VehicleAssignments());
        return "vehicleAssignment";
    }

    // Get VehicleAssignment by ID
    @GetMapping("/findById/{id}")
    public String getVehicleAssignmentById(@PathVariable("id") Integer id, Model model) {
        Optional<VehicleAssignments> assignment = vehicleAssignmentsService.getVehicleAssignmentById(id);
        if (assignment.isPresent()) {
            model.addAttribute("assignment", assignment.get());
        } else {
            model.addAttribute("assignment", new VehicleAssignments());
        }
        model.addAttribute("assignments", vehicleAssignmentsService.getAllVehicleAssignments());
        return "vehicle-assignments";
    }

    // Create new VehicleAssignment
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<VehicleAssignments> createVehicleAssignment(@RequestParam("vehicleId") Integer vehicleId, 
            @RequestParam("driverId") Integer driverId, 
            @RequestParam("startDate") LocalDateTime startDate, 
            @RequestParam("endDate") LocalDateTime endDate, 
            @RequestParam("usage") String usage, 
            @RequestParam("startingOdometer") int startingOdometer, 
            @RequestParam("endingOdometer") int endingOdometer) {
        
        VehicleAssignments vehicleAssignment = new VehicleAssignments();
        
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        User operator = userService.getUserById(driverId);

        vehicleAssignment.setVehicle(vehicle);
        vehicleAssignment.setOperator(operator);
        vehicleAssignment.setStartTime(startDate);
        vehicleAssignment.setEndTime(endDate);
        vehicleAssignment.setUsage(usage);
        vehicleAssignment.setStartingOdometer(startingOdometer);
        vehicleAssignment.setEndingOdometer(endingOdometer);
        vehicleAssignment.setDuration(java.time.Duration.between(startDate, endDate).toSeconds());

        vehicleAssignmentsService.createVehicleAssignment(vehicleAssignment);

        return ResponseEntity.ok(vehicleAssignment);
    }

    // delete mapping
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteVehicleAssignment(@PathVariable("id") Integer id) {
        Map<String, String> response = new HashMap<>();
        try {
            boolean isDeleted = vehicleAssignmentsService.deleteVehicleAssignment(id);
            if (isDeleted) {
                response.put("message", "Vehicle assignment deleted successfully.");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Vehicle assignment not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("message", "An error occurred while deleting the vehicle assignment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<VehicleAssignments> updateVehicleAssignment(@PathVariable("id") Integer id,
                                                                      @RequestParam("vehicleId") Integer vehicleId,
                                                                      @RequestParam("driverId") Integer driverId,
                                                                      @RequestParam("startDate") LocalDateTime startDate,
                                                                      @RequestParam("endDate") LocalDateTime endDate,
                                                                      @RequestParam("usage") String usage,
                                                                      @RequestParam("startingOdometer") int startingOdometer,
                                                                      @RequestParam("endingOdometer") int endingOdometer) {
        Optional<VehicleAssignments> existingAssignment = vehicleAssignmentsService.getVehicleAssignmentById(id);
        if (!existingAssignment.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        VehicleAssignments vehicleAssignment = existingAssignment.get();

        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        User operator = userService.getUserById(driverId);

        vehicleAssignment.setVehicle(vehicle);
        vehicleAssignment.setOperator(operator);
        vehicleAssignment.setStartTime(startDate);
        vehicleAssignment.setEndTime(endDate);
        vehicleAssignment.setUsage(usage);
        vehicleAssignment.setStartingOdometer(startingOdometer);
        vehicleAssignment.setEndingOdometer(endingOdometer);
        vehicleAssignment.setDuration(java.time.Duration.between(startDate, endDate).toSeconds());

        vehicleAssignmentsService.createVehicleAssignment(vehicleAssignment);

        return ResponseEntity.ok(vehicleAssignment);
    }
}
