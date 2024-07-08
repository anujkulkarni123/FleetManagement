package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.ContactReminder;
import com.exavalu.fleetmanagementapp.services.ContactReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-reminders")
public class ContactReminderController {

    @Autowired
    private ContactReminderService contactReminderService;

    // Get all ContactReminders
    @GetMapping
    public List<ContactReminder> getAllContactReminders() {
        return contactReminderService.getAllContactReminders();
    }

    // Get ContactReminder by ID
    @GetMapping("/{id}")
    public ResponseEntity<ContactReminder> getContactReminderById(@PathVariable Integer id) {
        return contactReminderService.getContactReminderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new ContactReminder
    @PostMapping
    public ContactReminder createContactReminder(@RequestBody ContactReminder contactReminder) {
        return contactReminderService.createContactReminder(contactReminder);
    }

    // Update existing ContactReminder
    @PutMapping("/{id}")
    public ResponseEntity<ContactReminder> updateContactReminder(@PathVariable Integer id, @RequestBody ContactReminder contactReminderDetails) {
        return contactReminderService.updateContactReminder(id, contactReminderDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete ContactReminder
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactReminder(@PathVariable Integer id) {
        if (contactReminderService.deleteContactReminder(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
