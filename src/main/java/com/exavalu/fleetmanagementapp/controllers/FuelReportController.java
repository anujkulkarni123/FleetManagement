package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.FuelReport;
import com.exavalu.fleetmanagementapp.services.FuelReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuelReports")
public class FuelReportController {

    @Autowired
    private FuelReportService fuelReportService;

    // Get all Fuel Reports
    @GetMapping
    public List<FuelReport> getAllFuelReports() {
        return fuelReportService.getAllFuelReports();
    }

    // Get Fuel Report by ID
    @GetMapping("/{id}")
    public ResponseEntity<FuelReport> getFuelReportById(@PathVariable Integer id) {
        return fuelReportService.getFuelReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Fuel Report
    @PostMapping
    public FuelReport createFuelReport(@RequestBody FuelReport fuelReport) {
        return fuelReportService.createFuelReport(fuelReport);
    }

    // Update existing Fuel Report
    @PutMapping("/{id}")
    public ResponseEntity<FuelReport> updateFuelReport(@PathVariable Integer id, @RequestBody FuelReport fuelReportDetails) {
        return fuelReportService.updateFuelReport(id, fuelReportDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Fuel Report
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuelReport(@PathVariable Integer id) {
        if (fuelReportService.deleteFuelReport(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
