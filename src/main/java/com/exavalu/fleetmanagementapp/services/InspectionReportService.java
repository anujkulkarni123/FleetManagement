package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.InspectionReport;
import com.exavalu.fleetmanagementapp.repositories.InspectionReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InspectionReportService {

    @Autowired
    private InspectionReportRepository inspectionReportRepository;

    public List<InspectionReport> getAllInspectionReports() {
        return inspectionReportRepository.findAll();
    }

    public Optional<InspectionReport> getInspectionReportById(Integer id) {
        return inspectionReportRepository.findById(id);
    }

    public InspectionReport createInspectionReport(InspectionReport inspectionReport) {
        return inspectionReportRepository.save(inspectionReport);
    }

    public Optional<InspectionReport> updateInspectionReport(Integer id, InspectionReport inspectionReportDetails) {
        return inspectionReportRepository.findById(id).map(inspectionReport -> {
            inspectionReport.setReportForm(inspectionReportDetails.getReportForm());
            return inspectionReportRepository.save(inspectionReport);
        });
    }

    public boolean deleteInspectionReport(Integer id) {
        return inspectionReportRepository.findById(id).map(inspectionReport -> {
            inspectionReportRepository.delete(inspectionReport);
            return true;
        }).orElse(false);
    }
}
