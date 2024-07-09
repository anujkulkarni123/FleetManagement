package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.FleetService;
import com.exavalu.fleetmanagementapp.services.FleetServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class FleetServiceController {

    @Autowired
    private FleetServiceService serviceService;

    // Get all Services
    @GetMapping
    public List<FleetService> getAllServices() {
        return serviceService.getAllServices();
    }

    // Get Service by ID
    @GetMapping("/{id}")
    public ResponseEntity<FleetService> getServiceById(@PathVariable Integer id) {
        return serviceService.getServiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Service
    @PostMapping
    public FleetService createService(@RequestBody FleetService service) {
        return serviceService.createService(service);
    }

    // Update existing Service
    @PutMapping("/{id}")
    public ResponseEntity<FleetService> updateService(@PathVariable Integer id, @RequestBody FleetService serviceDetails) {
        return serviceService.updateService(id, serviceDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Service
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Integer id) {
        if (serviceService.deleteService(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
