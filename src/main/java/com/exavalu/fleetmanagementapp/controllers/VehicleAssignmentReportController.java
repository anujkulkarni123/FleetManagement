package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.VehicleAssignmentReport;
import com.exavalu.fleetmanagementapp.services.VehicleAssignmentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-assignment-reports")
public class VehicleAssignmentReportController {

    @Autowired
    private VehicleAssignmentReportService vehicleAssignmentReportService;

    // Get all VehicleAssignmentReports
    @GetMapping
    public List<VehicleAssignmentReport> getAllVehicleAssignmentReports() {
        return vehicleAssignmentReportService.getAllVehicleAssignmentReports();
    }

    // Get VehicleAssignmentReport by ID
    @GetMapping("/{id}")
    public ResponseEntity<VehicleAssignmentReport> getVehicleAssignmentReportById(@PathVariable Integer id) {
        return vehicleAssignmentReportService.getVehicleAssignmentReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new VehicleAssignmentReport
    @PostMapping
    public VehicleAssignmentReport createVehicleAssignmentReport(@RequestBody VehicleAssignmentReport vehicleAssignmentReport) {
        return vehicleAssignmentReportService.createVehicleAssignmentReport(vehicleAssignmentReport);
    }

    // Update existing VehicleAssignmentReport
    @PutMapping("/{id}")
    public ResponseEntity<VehicleAssignmentReport> updateVehicleAssignmentReport(@PathVariable Integer id, @RequestBody VehicleAssignmentReport vehicleAssignmentReportDetails) {
        return vehicleAssignmentReportService.updateVehicleAssignmentReport(id, vehicleAssignmentReportDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete VehicleAssignmentReport
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleAssignmentReport(@PathVariable Integer id) {
        if (vehicleAssignmentReportService.deleteVehicleAssignmentReport(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
