package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.IssuesReport;
import com.exavalu.fleetmanagementapp.services.IssuesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues-reports")
public class IssuesReportController {

    @Autowired
    private IssuesReportService issuesReportService;

    // Get all IssuesReports
    @GetMapping
    public List<IssuesReport> getAllIssuesReports() {
        return issuesReportService.getAllIssuesReports();
    }

    // Get IssuesReport by ID
    @GetMapping("/{id}")
    public ResponseEntity<IssuesReport> getIssuesReportById(@PathVariable Integer id) {
        return issuesReportService.getIssuesReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new IssuesReport
    @PostMapping
    public IssuesReport createIssuesReport(@RequestBody IssuesReport issuesReport) {
        return issuesReportService.createIssuesReport(issuesReport);
    }

    // Update existing IssuesReport
    @PutMapping("/{id}")
    public ResponseEntity<IssuesReport> updateIssuesReport(@PathVariable Integer id, @RequestBody IssuesReport issuesReportDetails) {
        return issuesReportService.updateIssuesReport(id, issuesReportDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete IssuesReport
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssuesReport(@PathVariable Integer id) {
        if (issuesReportService.deleteIssuesReport(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
