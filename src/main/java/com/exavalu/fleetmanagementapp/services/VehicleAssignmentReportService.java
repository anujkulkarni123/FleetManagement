package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.VehicleAssignmentReport;
import com.exavalu.fleetmanagementapp.repositories.VehicleAssignmentReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleAssignmentReportService {

    @Autowired
    private VehicleAssignmentReportRepository vehicleAssignmentReportRepository;

    public List<VehicleAssignmentReport> getAllVehicleAssignmentReports() {
        return vehicleAssignmentReportRepository.findAll();
    }

    public Optional<VehicleAssignmentReport> getVehicleAssignmentReportById(Integer id) {
        return vehicleAssignmentReportRepository.findById(id);
    }

    public VehicleAssignmentReport createVehicleAssignmentReport(VehicleAssignmentReport vehicleAssignmentReport) {
        return vehicleAssignmentReportRepository.save(vehicleAssignmentReport);
    }

    public Optional<VehicleAssignmentReport> updateVehicleAssignmentReport(Integer id, VehicleAssignmentReport vehicleAssignmentReportDetails) {
        return vehicleAssignmentReportRepository.findById(id).map(vehicleAssignmentReport -> {
            vehicleAssignmentReport.setReportForm(vehicleAssignmentReportDetails.getReportForm());
            // Add other fields to update as necessary
            return vehicleAssignmentReportRepository.save(vehicleAssignmentReport);
        });
    }

    public boolean deleteVehicleAssignmentReport(Integer id) {
        return vehicleAssignmentReportRepository.findById(id).map(vehicleAssignmentReport -> {
            vehicleAssignmentReportRepository.delete(vehicleAssignmentReport);
            return true;
        }).orElse(false);
    }
}
