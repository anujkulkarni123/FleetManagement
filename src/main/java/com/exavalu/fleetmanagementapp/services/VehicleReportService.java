package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.VehicleReport;
import com.exavalu.fleetmanagementapp.repositories.VehicleReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleReportService {

    @Autowired
    private VehicleReportRepository vehicleReportRepository;

    public List<VehicleReport> getAllVehicleReports() {
        return vehicleReportRepository.findAll();
    }

    public Optional<VehicleReport> getVehicleReportById(Integer id) {
        return vehicleReportRepository.findById(id);
    }

    public VehicleReport createVehicleReport(VehicleReport vehicleReport) {
        return vehicleReportRepository.save(vehicleReport);
    }

    public Optional<VehicleReport> updateVehicleReport(Integer id, VehicleReport vehicleReportDetails) {
        return vehicleReportRepository.findById(id).map(vehicleReport -> {
            vehicleReport.setReportForm(vehicleReportDetails.getReportForm());
            vehicleReport.setTeam(vehicleReportDetails.getTeam());
            vehicleReport.setReportType(vehicleReportDetails.getReportType());
            vehicleReport.setFavourite(vehicleReportDetails.isFavourite());
            vehicleReport.setSaved(vehicleReportDetails.isSaved());
            vehicleReport.setShared(vehicleReportDetails.isShared());
            return vehicleReportRepository.save(vehicleReport);
        });
    }

    public boolean deleteVehicleReport(Integer id) {
        return vehicleReportRepository.findById(id).map(vehicleReport -> {
            vehicleReportRepository.delete(vehicleReport);
            return true;
        }).orElse(false);
    }
}
