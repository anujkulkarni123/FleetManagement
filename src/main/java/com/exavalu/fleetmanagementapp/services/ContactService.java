package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.Contact;
import com.exavalu.fleetmanagementapp.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Integer id) {
        return contactRepository.findById(id);
    }

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Optional<Contact> updateContact(Integer id, Contact contactDetails) {
        return contactRepository.findById(id).map(contact -> {
            contact.setName(contactDetails.getName());
            contact.setEmail(contactDetails.getEmail());
            contact.setUserStatus(contactDetails.getUserStatus());
            contact.setUserType(contactDetails.getUserType());
            contact.setUserRole(contactDetails.getUserRole());
            contact.setNumberLogins(contactDetails.getNumberLogins());
            contact.setClassifications(contactDetails.getClassifications());
            contact.setGroupField(contactDetails.getGroupField());
            contact.setAssignedVehicles(contactDetails.getAssignedVehicles());
            contact.setAssignedEquipment(contactDetails.getAssignedEquipment());
            return contactRepository.save(contact);
        });
    }

    public boolean deleteContact(Integer id) {
        return contactRepository.findById(id).map(contact -> {
            contactRepository.delete(contact);
            return true;
        }).orElse(false);
    }
}
