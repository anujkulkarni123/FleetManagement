package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.InspectionReport;
import com.exavalu.fleetmanagementapp.services.InspectionReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inspectionReports")
public class InspectionReportController {

    @Autowired
    private InspectionReportService inspectionReportService;

    // Get all Inspection Reports
    @GetMapping
    public List<InspectionReport> getAllInspectionReports() {
        return inspectionReportService.getAllInspectionReports();
    }

    // Get Inspection Report by ID
    @GetMapping("/{id}")
    public ResponseEntity<InspectionReport> getInspectionReportById(@PathVariable Integer id) {
        return inspectionReportService.getInspectionReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Inspection Report
    @PostMapping
    public InspectionReport createInspectionReport(@RequestBody InspectionReport inspectionReport) {
        return inspectionReportService.createInspectionReport(inspectionReport);
    }

    // Update existing Inspection Report
    @PutMapping("/{id}")
    public ResponseEntity<InspectionReport> updateInspectionReport(@PathVariable Integer id, @RequestBody InspectionReport inspectionReportDetails) {
        return inspectionReportService.updateInspectionReport(id, inspectionReportDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Inspection Report
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspectionReport(@PathVariable Integer id) {
        if (inspectionReportService.deleteInspectionReport(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
