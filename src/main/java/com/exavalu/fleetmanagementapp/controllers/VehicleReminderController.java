package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.VehicleReminder;
import com.exavalu.fleetmanagementapp.services.VehicleReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-reminders")
public class VehicleReminderController {

    @Autowired
    private VehicleReminderService vehicleReminderService;

    // Get all VehicleReminders
    @GetMapping
    public List<VehicleReminder> getAllVehicleReminders() {
        return vehicleReminderService.getAllVehicleReminders();
    }

    // Get VehicleReminder by ID
    @GetMapping("/{id}")
    public ResponseEntity<VehicleReminder> getVehicleReminderById(@PathVariable Integer id) {
        return vehicleReminderService.getVehicleReminderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new VehicleReminder
    @PostMapping
    public VehicleReminder createVehicleReminder(@RequestBody VehicleReminder vehicleReminder) {
        return vehicleReminderService.createVehicleReminder(vehicleReminder);
    }

    // Update existing VehicleReminder
    @PutMapping("/{id}")
    public ResponseEntity<VehicleReminder> updateVehicleReminder(@PathVariable Integer id, @RequestBody VehicleReminder vehicleReminderDetails) {
        return vehicleReminderService.updateVehicleReminder(id, vehicleReminderDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete VehicleReminder
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleReminder(@PathVariable Integer id) {
        if (vehicleReminderService.deleteVehicleReminder(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
