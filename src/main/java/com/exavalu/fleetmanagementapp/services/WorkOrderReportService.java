package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.WorkOrderReport;
import com.exavalu.fleetmanagementapp.repositories.WorkOrderReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkOrderReportService {

    @Autowired
    private WorkOrderReportRepository workOrderReportRepository;

    public List<WorkOrderReport> getAllWorkOrderReports() {
        return workOrderReportRepository.findAll();
    }

    public Optional<WorkOrderReport> getWorkOrderReportById(Integer id) {
        return workOrderReportRepository.findById(id);
    }

    public WorkOrderReport createWorkOrderReport(WorkOrderReport workOrderReport) {
        return workOrderReportRepository.save(workOrderReport);
    }

    public Optional<WorkOrderReport> updateWorkOrderReport(Integer id, WorkOrderReport workOrderReportDetails) {
        return workOrderReportRepository.findById(id).map(workOrderReport -> {
            workOrderReport.setReportForm(workOrderReportDetails.getReportForm());
            workOrderReport.setTeam(workOrderReportDetails.getTeam());
            workOrderReport.setFavourite(workOrderReportDetails.isFavourite());
            workOrderReport.setSaved(workOrderReportDetails.isSaved());
            workOrderReport.setShared(workOrderReportDetails.isShared());
            return workOrderReportRepository.save(workOrderReport);
        });
    }

    public boolean deleteWorkOrderReport(Integer id) {
        return workOrderReportRepository.findById(id).map(workOrderReport -> {
            workOrderReportRepository.delete(workOrderReport);
            return true;
        }).orElse(false);
    }
}
