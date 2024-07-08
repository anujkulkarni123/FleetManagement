package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.ServiceReminder;
import com.exavalu.fleetmanagementapp.services.ServiceReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-reminders")
public class ServiceReminderController {

    @Autowired
    private ServiceReminderService serviceReminderService;

    // Get all Service Reminders
    @GetMapping
    public List<ServiceReminder> getAllServiceReminders() {
        return serviceReminderService.getAllServiceReminders();
    }

    // Get Service Reminder by ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceReminder> getServiceReminderById(@PathVariable Integer id) {
        return serviceReminderService.getServiceReminderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Service Reminder
    @PostMapping
    public ServiceReminder createServiceReminder(@RequestBody ServiceReminder serviceReminder) {
        return serviceReminderService.createServiceReminder(serviceReminder);
    }

    // Update existing Service Reminder
    @PutMapping("/{id}")
    public ResponseEntity<ServiceReminder> updateServiceReminder(@PathVariable Integer id, @RequestBody ServiceReminder serviceReminderDetails) {
        return serviceReminderService.updateServiceReminder(id, serviceReminderDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Service Reminder
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceReminder(@PathVariable Integer id) {
        if (serviceReminderService.deleteServiceReminder(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
