package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.ServiceReport;
import com.exavalu.fleetmanagementapp.services.ServiceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-reports")
public class ServiceReportController {

    @Autowired
    private ServiceReportService serviceReportService;

    // Get all Service Reports
    @GetMapping
    public List<ServiceReport> getAllServiceReports() {
        return serviceReportService.getAllServiceReports();
    }

    // Get Service Report by ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceReport> getServiceReportById(@PathVariable Integer id) {
        return serviceReportService.getServiceReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Service Report
    @PostMapping
    public ServiceReport createServiceReport(@RequestBody ServiceReport serviceReport) {
        return serviceReportService.createServiceReport(serviceReport);
    }

    // Update existing Service Report
    @PutMapping("/{id}")
    public ResponseEntity<ServiceReport> updateServiceReport(@PathVariable Integer id, @RequestBody ServiceReport serviceReportDetails) {
        return serviceReportService.updateServiceReport(id, serviceReportDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Service Report
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceReport(@PathVariable Integer id) {
        if (serviceReportService.deleteServiceReport(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
