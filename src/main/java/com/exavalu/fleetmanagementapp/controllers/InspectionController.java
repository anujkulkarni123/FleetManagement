package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.Inspections;
import com.exavalu.fleetmanagementapp.services.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inspections")
public class InspectionController {

    @Autowired
    private InspectionService inspectionService;

    // Get all Inspections
    @GetMapping
    public List<Inspections> getAllInspections() {
        return inspectionService.getAllInspections();
    }

    // Get Inspection by ID
    @GetMapping("/{id}")
    public ResponseEntity<Inspections> getInspectionById(@PathVariable Integer id) {
        return inspectionService.getInspectionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Inspection
    @PostMapping
    public Inspections createInspection(@RequestBody Inspections inspection) {
        return inspectionService.createInspection(inspection);
    }

    // Update existing Inspection
    @PutMapping("/{id}")
    public ResponseEntity<Inspections> updateInspection(@PathVariable Integer id, @RequestBody Inspections inspectionDetails) {
        return inspectionService.updateInspection(id, inspectionDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Inspection
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspection(@PathVariable Integer id) {
        if (inspectionService.deleteInspection(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
