package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.VehicleReminder;
import com.exavalu.fleetmanagementapp.repositories.VehicleReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleReminderService {

    @Autowired
    private VehicleReminderRepository vehicleReminderRepository;

    public List<VehicleReminder> getAllVehicleReminders() {
        return vehicleReminderRepository.findAll();
    }

    public Optional<VehicleReminder> getVehicleReminderById(Integer id) {
        return vehicleReminderRepository.findById(id);
    }

    public VehicleReminder createVehicleReminder(VehicleReminder vehicleReminder) {
        return vehicleReminderRepository.save(vehicleReminder);
    }

    public Optional<VehicleReminder> updateVehicleReminder(Integer id, VehicleReminder vehicleReminderDetails) {
        return vehicleReminderRepository.findById(id).map(vehicleReminder -> {
            vehicleReminder.setRenewalType(vehicleReminderDetails.getRenewalType());
            vehicleReminder.setWatchers(vehicleReminderDetails.getWatchers());
            vehicleReminder.setStatus(vehicleReminderDetails.getStatus());
            vehicleReminder.setNextDueDate(vehicleReminderDetails.getNextDueDate());
            vehicleReminder.setTeam(vehicleReminderDetails.getTeam());
            vehicleReminder.setVehicle(vehicleReminderDetails.getVehicle());
            return vehicleReminderRepository.save(vehicleReminder);
        });
    }

    public boolean deleteVehicleReminder(Integer id) {
        return vehicleReminderRepository.findById(id).map(vehicleReminder -> {
            vehicleReminderRepository.delete(vehicleReminder);
            return true;
        }).orElse(false);
    }
}
