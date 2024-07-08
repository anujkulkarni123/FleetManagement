package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.ContactReport;
import com.exavalu.fleetmanagementapp.services.ContactReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-reports")
public class ContactReportController {

    @Autowired
    private ContactReportService contactReportService;

    // Get all ContactReports
    @GetMapping
    public List<ContactReport> getAllContactReports() {
        return contactReportService.getAllContactReports();
    }

    // Get ContactReport by ID
    @GetMapping("/{id}")
    public ResponseEntity<ContactReport> getContactReportById(@PathVariable Integer id) {
        return contactReportService.getContactReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new ContactReport
    @PostMapping
    public ContactReport createContactReport(@RequestBody ContactReport contactReport) {
        return contactReportService.createContactReport(contactReport);
    }

    // Update existing ContactReport
    @PutMapping("/{id}")
    public ResponseEntity<ContactReport> updateContactReport(@PathVariable Integer id, @RequestBody ContactReport contactReportDetails) {
        return contactReportService.updateContactReport(id, contactReportDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete ContactReport
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactReport(@PathVariable Integer id) {
        if (contactReportService.deleteContactReport(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
