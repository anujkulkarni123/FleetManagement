package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.Issues;
import com.exavalu.fleetmanagementapp.services.IssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssuesController {

    @Autowired
    private IssuesService issuesService;

    // Get all Issues
    @GetMapping
    public List<Issues> getAllIssues() {
        return issuesService.getAllIssues();
    }

    // Get Issue by ID
    @GetMapping("/{id}")
    public ResponseEntity<Issues> getIssueById(@PathVariable Integer id) {
        return issuesService.getIssuesById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Issue
    @PostMapping
    public Issues createIssue(@RequestBody Issues issues) {
        return issuesService.createIssues(issues);
    }

    // Update existing Issue
    @PutMapping("/{id}")
    public ResponseEntity<Issues> updateIssue(@PathVariable Integer id, @RequestBody Issues issuesDetails) {
        return issuesService.updateIssues(id, issuesDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Issue
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Integer id) {
        if (issuesService.deleteIssues(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
