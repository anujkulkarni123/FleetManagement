package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.VehicleReport;
import com.exavalu.fleetmanagementapp.services.VehicleReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-reports")
public class VehicleReportController {

    @Autowired
    private VehicleReportService vehicleReportService;

    // Get all VehicleReports
    @GetMapping
    public List<VehicleReport> getAllVehicleReports() {
        return vehicleReportService.getAllVehicleReports();
    }

    // Get VehicleReport by ID
    @GetMapping("/{id}")
    public ResponseEntity<VehicleReport> getVehicleReportById(@PathVariable Integer id) {
        return vehicleReportService.getVehicleReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new VehicleReport
    @PostMapping
    public VehicleReport createVehicleReport(@RequestBody VehicleReport vehicleReport) {
        return vehicleReportService.createVehicleReport(vehicleReport);
    }

    // Update existing VehicleReport
    @PutMapping("/{id}")
    public ResponseEntity<VehicleReport> updateVehicleReport(@PathVariable Integer id, @RequestBody VehicleReport vehicleReportDetails) {
        return vehicleReportService.updateVehicleReport(id, vehicleReportDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete VehicleReport
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleReport(@PathVariable Integer id) {
        if (vehicleReportService.deleteVehicleReport(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
