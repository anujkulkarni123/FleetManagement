package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.Report;
import com.exavalu.fleetmanagementapp.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(Integer id) {
        return reportRepository.findById(id);
    }

    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    public Optional<Report> updateReport(Integer id, Report reportDetails) {
        return reportRepository.findById(id).map(report -> {
            report.setTeam(reportDetails.getTeam());
            report.setReportType(reportDetails.getReportType());
            report.setFavourite(reportDetails.isFavourite());
            report.setSaved(reportDetails.isSaved());
            report.setShared(reportDetails.isShared());
            return reportRepository.save(report);
        });
    }

    public boolean deleteReport(Integer id) {
        return reportRepository.findById(id).map(report -> {
            reportRepository.delete(report);
            return true;
        }).orElse(false);
    }
}
