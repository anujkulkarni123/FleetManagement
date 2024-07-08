package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.Fault;
import com.exavalu.fleetmanagementapp.services.FaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faults")
public class FaultController {

    @Autowired
    private FaultService faultService;

    // Get all Faults
    @GetMapping
    public List<Fault> getAllFaults() {
        return faultService.getAllFaults();
    }

    // Get Fault by ID
    @GetMapping("/{id}")
    public ResponseEntity<Fault> getFaultById(@PathVariable Integer id) {
        return faultService.getFaultById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Fault
    @PostMapping
    public Fault createFault(@RequestBody Fault fault) {
        return faultService.createFault(fault);
    }

    // Update existing Fault
    @PutMapping("/{id}")
    public ResponseEntity<Fault> updateFault(@PathVariable Integer id, @RequestBody Fault faultDetails) {
        return faultService.updateFault(id, faultDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Fault
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFault(@PathVariable Integer id) {
        if (faultService.deleteFault(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
