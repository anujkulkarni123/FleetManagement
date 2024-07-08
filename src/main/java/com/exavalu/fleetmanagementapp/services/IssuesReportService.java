package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.IssuesReport;
import com.exavalu.fleetmanagementapp.repositories.IssuesReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssuesReportService {

    @Autowired
    private IssuesReportRepository issuesReportRepository;

    public List<IssuesReport> getAllIssuesReports() {
        return issuesReportRepository.findAll();
    }

    public Optional<IssuesReport> getIssuesReportById(Integer id) {
        return issuesReportRepository.findById(id);
    }

    public IssuesReport createIssuesReport(IssuesReport issuesReport) {
        return issuesReportRepository.save(issuesReport);
    }

    public Optional<IssuesReport> updateIssuesReport(Integer id, IssuesReport issuesReportDetails) {
        return issuesReportRepository.findById(id).map(issuesReport -> {
            issuesReport.setReportForm(issuesReportDetails.getReportForm());
            return issuesReportRepository.save(issuesReport);
        });
    }

    public boolean deleteIssuesReport(Integer id) {
        return issuesReportRepository.findById(id).map(issuesReport -> {
            issuesReportRepository.delete(issuesReport);
            return true;
        }).orElse(false);
    }
}
