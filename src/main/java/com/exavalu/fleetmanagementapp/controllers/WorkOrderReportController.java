package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.WorkOrderReport;
import com.exavalu.fleetmanagementapp.services.WorkOrderReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workorder-reports")
public class WorkOrderReportController {

    @Autowired
    private WorkOrderReportService workOrderReportService;

    // Get all WorkOrderReports
    @GetMapping
    public List<WorkOrderReport> getAllWorkOrderReports() {
        return workOrderReportService.getAllWorkOrderReports();
    }

    // Get WorkOrderReport by ID
    @GetMapping("/{id}")
    public ResponseEntity<WorkOrderReport> getWorkOrderReportById(@PathVariable Integer id) {
        return workOrderReportService.getWorkOrderReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new WorkOrderReport
    @PostMapping
    public WorkOrderReport createWorkOrderReport(@RequestBody WorkOrderReport workOrderReport) {
        return workOrderReportService.createWorkOrderReport(workOrderReport);
    }

    // Update existing WorkOrderReport
    @PutMapping("/{id}")
    public ResponseEntity<WorkOrderReport> updateWorkOrderReport(@PathVariable Integer id, @RequestBody WorkOrderReport workOrderReportDetails) {
        return workOrderReportService.updateWorkOrderReport(id, workOrderReportDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete WorkOrderReport
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkOrderReport(@PathVariable Integer id) {
        if (workOrderReportService.deleteWorkOrderReport(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
