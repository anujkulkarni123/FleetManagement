package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.VehicleAssignments;
import com.exavalu.fleetmanagementapp.services.VehicleAssignmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-assignments")
public class VehicleAssignmentsController {

    @Autowired
    private VehicleAssignmentsService vehicleAssignmentsService;

    // Get all VehicleAssignments
    @GetMapping
    public List<VehicleAssignments> getAllVehicleAssignments() {
        return vehicleAssignmentsService.getAllVehicleAssignments();
    }

    // Get VehicleAssignment by ID
    @GetMapping("/{id}")
    public ResponseEntity<VehicleAssignments> getVehicleAssignmentById(@PathVariable Integer id) {
        return vehicleAssignmentsService.getVehicleAssignmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new VehicleAssignment
    @PostMapping
    public VehicleAssignments createVehicleAssignment(@RequestBody VehicleAssignments vehicleAssignment) {
        return vehicleAssignmentsService.createVehicleAssignment(vehicleAssignment);
    }

    // Update existing VehicleAssignment
    @PutMapping("/{id}")
    public ResponseEntity<VehicleAssignments> updateVehicleAssignment(@PathVariable Integer id, @RequestBody VehicleAssignments vehicleAssignmentDetails) {
        return vehicleAssignmentsService.updateVehicleAssignment(id, vehicleAssignmentDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete VehicleAssignment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleAssignment(@PathVariable Integer id) {
        if (vehicleAssignmentsService.deleteVehicleAssignment(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
