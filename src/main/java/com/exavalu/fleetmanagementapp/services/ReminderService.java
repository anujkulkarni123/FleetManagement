package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.Reminder;
import com.exavalu.fleetmanagementapp.repositories.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    public Optional<Reminder> getReminderById(Integer id) {
        return reminderRepository.findById(id);
    }

    public Reminder createReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    public Optional<Reminder> updateReminder(Integer id, Reminder reminderDetails) {
        return reminderRepository.findById(id).map(reminder -> {
            reminder.setTeam(reminderDetails.getTeam());
            reminder.setVehicle(reminderDetails.getVehicle());
            reminder.setStatus(reminderDetails.getStatus());
            reminder.setNextDueDate(reminderDetails.getNextDueDate());
            return reminderRepository.save(reminder);
        });
    }

    public boolean deleteReminder(Integer id) {
        return reminderRepository.findById(id).map(reminder -> {
            reminderRepository.delete(reminder);
            return true;
        }).orElse(false);
    }
}
