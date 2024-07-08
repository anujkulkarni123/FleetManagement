package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.NotificationSettings;
import com.exavalu.fleetmanagementapp.repositories.NotificationSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationSettingsService {

    @Autowired
    private NotificationSettingsRepository notificationSettingsRepository;

    public List<NotificationSettings> getAllNotificationSettings() {
        return notificationSettingsRepository.findAll();
    }

    public Optional<NotificationSettings> getNotificationSettingsById(Integer id) {
        return notificationSettingsRepository.findById(id);
    }

    public NotificationSettings createNotificationSettings(NotificationSettings notificationSettings) {
        return notificationSettingsRepository.save(notificationSettings);
    }

    public Optional<NotificationSettings> updateNotificationSettings(Integer id, NotificationSettings notificationSettingsDetails) {
        return notificationSettingsRepository.findById(id).map(notificationSettings -> {
            notificationSettings.setUser(notificationSettingsDetails.getUser());
            notificationSettings.setVehicleNotif(notificationSettingsDetails.isVehicleNotif());
            notificationSettings.setInspectionNotif(notificationSettingsDetails.isInspectionNotif());
            notificationSettings.setIssueNotif(notificationSettingsDetails.isIssueNotif());
            notificationSettings.setWorkOrderNotif(notificationSettingsDetails.isWorkOrderNotif());
            notificationSettings.setServiceEntryNotif(notificationSettingsDetails.isServiceEntryNotif());
            notificationSettings.setServiceReminderNotif(notificationSettingsDetails.isServiceReminderNotif());
            notificationSettings.setVehicleReminderNotif(notificationSettingsDetails.isVehicleReminderNotif());
            notificationSettings.setContactReminderNotif(notificationSettingsDetails.isContactReminderNotif());
            notificationSettings.setFuelEntryNotif(notificationSettingsDetails.isFuelEntryNotif());
            notificationSettings.setChargingEntryNotif(notificationSettingsDetails.isChargingEntryNotif());
            notificationSettings.setPartsNotif(notificationSettingsDetails.isPartsNotif());
            notificationSettings.setVendorNotifs(notificationSettingsDetails.isVendorNotifs());
            notificationSettings.setExpenseNotif(notificationSettingsDetails.isExpenseNotif());
            notificationSettings.setIntegrationNotif(notificationSettingsDetails.isIntegrationNotif());
            return notificationSettingsRepository.save(notificationSettings);
        });
    }

    public boolean deleteNotificationSettings(Integer id) {
        return notificationSettingsRepository.findById(id).map(notificationSettings -> {
            notificationSettingsRepository.delete(notificationSettings);
            return true;
        }).orElse(false);
    }
}
