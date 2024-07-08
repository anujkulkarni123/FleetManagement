package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.NotificationSettings;
import com.exavalu.fleetmanagementapp.services.NotificationSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification-settings")
public class NotificationSettingsController {

    @Autowired
    private NotificationSettingsService notificationSettingsService;

    // Get all NotificationSettings
    @GetMapping
    public List<NotificationSettings> getAllNotificationSettings() {
        return notificationSettingsService.getAllNotificationSettings();
    }

    // Get NotificationSettings by ID
    @GetMapping("/{id}")
    public ResponseEntity<NotificationSettings> getNotificationSettingsById(@PathVariable Integer id) {
        return notificationSettingsService.getNotificationSettingsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new NotificationSettings
    @PostMapping
    public NotificationSettings createNotificationSettings(@RequestBody NotificationSettings notificationSettings) {
        return notificationSettingsService.createNotificationSettings(notificationSettings);
    }

    // Update existing NotificationSettings
    @PutMapping("/{id}")
    public ResponseEntity<NotificationSettings> updateNotificationSettings(@PathVariable Integer id, @RequestBody NotificationSettings notificationSettingsDetails) {
        return notificationSettingsService.updateNotificationSettings(id, notificationSettingsDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete NotificationSettings
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificationSettings(@PathVariable Integer id) {
        if (notificationSettingsService.deleteNotificationSettings(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
