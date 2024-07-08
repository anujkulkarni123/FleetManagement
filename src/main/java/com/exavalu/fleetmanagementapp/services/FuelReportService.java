package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.FuelReport;
import com.exavalu.fleetmanagementapp.repositories.FuelReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuelReportService {

    @Autowired
    private FuelReportRepository fuelReportRepository;

    public List<FuelReport> getAllFuelReports() {
        return fuelReportRepository.findAll();
    }

    public Optional<FuelReport> getFuelReportById(Integer id) {
        return fuelReportRepository.findById(id);
    }

    public FuelReport createFuelReport(FuelReport fuelReport) {
        return fuelReportRepository.save(fuelReport);
    }

    public Optional<FuelReport> updateFuelReport(Integer id, FuelReport fuelReportDetails) {
        return fuelReportRepository.findById(id).map(fuelReport -> {
            fuelReport.setReportForm(fuelReportDetails.getReportForm());
            return fuelReportRepository.save(fuelReport);
        });
    }

    public boolean deleteFuelReport(Integer id) {
        return fuelReportRepository.findById(id).map(fuelReport -> {
            fuelReportRepository.delete(fuelReport);
            return true;
        }).orElse(false);
    }
}
