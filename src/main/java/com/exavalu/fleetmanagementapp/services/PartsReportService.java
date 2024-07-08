package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.PartsReport;
import com.exavalu.fleetmanagementapp.repositories.PartsReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartsReportService {

    @Autowired
    private PartsReportRepository partsReportRepository;

    public List<PartsReport> getAllPartsReports() {
        return partsReportRepository.findAll();
    }

    public Optional<PartsReport> getPartsReportById(Integer id) {
        return partsReportRepository.findById(id);
    }

    public PartsReport createPartsReport(PartsReport partsReport) {
        return partsReportRepository.save(partsReport);
    }

    public Optional<PartsReport> updatePartsReport(Integer id, PartsReport partsReportDetails) {
        return partsReportRepository.findById(id).map(partsReport -> {
            partsReport.setReportForm(partsReportDetails.getReportForm());
            // Update other fields if needed
            return partsReportRepository.save(partsReport);
        });
    }

    public boolean deletePartsReport(Integer id) {
        return partsReportRepository.findById(id).map(partsReport -> {
            partsReportRepository.delete(partsReport);
            return true;
        }).orElse(false);
    }
}
