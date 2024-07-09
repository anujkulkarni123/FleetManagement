package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.Part;
import com.exavalu.fleetmanagementapp.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
public class PartController {

    @Autowired
    private PartService partService;

    // Get all Parts
    @GetMapping
    public List<Part> getAllParts() {
        return partService.getAllParts();
    }

    // Get Part by ID
    @GetMapping("/{id}")
    public ResponseEntity<Part> getPartById(@PathVariable Integer id) {
        return partService.getPartById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Part
    @PostMapping
    public Part createPart(@RequestBody Part part) {
        return partService.createPart(part);
    }

    // Update existing Part
    @PutMapping("/{id}")
    public ResponseEntity<Part> updatePart(@PathVariable Integer id, @RequestBody Part partDetails) {
        return partService.updatePart(id, partDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Part
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePart(@PathVariable Integer id) {
        if (partService.deletePart(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
