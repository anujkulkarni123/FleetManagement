package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.ServiceProgram;
import com.exavalu.fleetmanagementapp.services.ServiceProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicePrograms")
public class ServiceProgramController {

    @Autowired
    private ServiceProgramService serviceProgramService;

    // Get all Service Programs
    @GetMapping
    public List<ServiceProgram> getAllServicePrograms() {
        return serviceProgramService.getAllServicePrograms();
    }

    // Get Service Program by ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceProgram> getServiceProgramById(@PathVariable Integer id) {
        return serviceProgramService.getServiceProgramById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Service Program
    @PostMapping
    public ServiceProgram createServiceProgram(@RequestBody ServiceProgram serviceProgram) {
        return serviceProgramService.createServiceProgram(serviceProgram);
    }

    // Update existing Service Program
    @PutMapping("/{id}")
    public ResponseEntity<ServiceProgram> updateServiceProgram(@PathVariable Integer id, @RequestBody ServiceProgram serviceProgramDetails) {
        return serviceProgramService.updateServiceProgram(id, serviceProgramDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Service Program
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceProgram(@PathVariable Integer id) {
        if (serviceProgramService.deleteServiceProgram(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
