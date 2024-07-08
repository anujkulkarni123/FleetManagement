package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.PartsReport;
import com.exavalu.fleetmanagementapp.services.PartsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts-reports")
public class PartsReportController {

    @Autowired
    private PartsReportService partsReportService;

    // Get all PartsReports
    @GetMapping
    public List<PartsReport> getAllPartsReports() {
        return partsReportService.getAllPartsReports();
    }

    // Get PartsReport by ID
    @GetMapping("/{id}")
    public ResponseEntity<PartsReport> getPartsReportById(@PathVariable Integer id) {
        return partsReportService.getPartsReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new PartsReport
    @PostMapping
    public PartsReport createPartsReport(@RequestBody PartsReport partsReport) {
        return partsReportService.createPartsReport(partsReport);
    }

    // Update existing PartsReport
    @PutMapping("/{id}")
    public ResponseEntity<PartsReport> updatePartsReport(@PathVariable Integer id, @RequestBody PartsReport partsReportDetails) {
        return partsReportService.updatePartsReport(id, partsReportDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete PartsReport
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartsReport(@PathVariable Integer id) {
        if (partsReportService.deletePartsReport(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
