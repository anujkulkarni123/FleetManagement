package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.ContactReport;
import com.exavalu.fleetmanagementapp.repositories.ContactReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactReportService {

    @Autowired
    private ContactReportRepository contactReportRepository;

    public List<ContactReport> getAllContactReports() {
        return contactReportRepository.findAll();
    }

    public Optional<ContactReport> getContactReportById(Integer id) {
        return contactReportRepository.findById(id);
    }

    public ContactReport createContactReport(ContactReport contactReport) {
        return contactReportRepository.save(contactReport);
    }

    public Optional<ContactReport> updateContactReport(Integer id, ContactReport contactReportDetails) {
        return contactReportRepository.findById(id).map(contactReport -> {
            contactReport.setReportForm(contactReportDetails.getReportForm());
            return contactReportRepository.save(contactReport);
        });
    }

    public boolean deleteContactReport(Integer id) {
        return contactReportRepository.findById(id).map(contactReport -> {
            contactReportRepository.delete(contactReport);
            return true;
        }).orElse(false);
    }
}
