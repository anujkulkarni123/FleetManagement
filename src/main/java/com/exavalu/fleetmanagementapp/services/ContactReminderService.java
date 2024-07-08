package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.ContactReminder;
import com.exavalu.fleetmanagementapp.repositories.ContactReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactReminderService {

    @Autowired
    private ContactReminderRepository contactReminderRepository;

    public List<ContactReminder> getAllContactReminders() {
        return contactReminderRepository.findAll();
    }

    public Optional<ContactReminder> getContactReminderById(Integer id) {
        return contactReminderRepository.findById(id);
    }

    public ContactReminder createContactReminder(ContactReminder contactReminder) {
        return contactReminderRepository.save(contactReminder);
    }

    public Optional<ContactReminder> updateContactReminder(Integer id, ContactReminder contactReminderDetails) {
        return contactReminderRepository.findById(id).map(contactReminder -> {
            contactReminder.setRenewalType(contactReminderDetails.getRenewalType());
            contactReminder.setDueDate(contactReminderDetails.getDueDate());
            contactReminder.setWatchers(contactReminderDetails.getWatchers());
            return contactReminderRepository.save(contactReminder);
        });
    }

    public boolean deleteContactReminder(Integer id) {
        return contactReminderRepository.findById(id).map(contactReminder -> {
            contactReminderRepository.delete(contactReminder);
            return true;
        }).orElse(false);
    }
}
