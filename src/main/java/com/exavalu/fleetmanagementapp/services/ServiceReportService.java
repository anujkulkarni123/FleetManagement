package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.ServiceReport;
import com.exavalu.fleetmanagementapp.repositories.ServiceReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceReportService {

    @Autowired
    private ServiceReportRepository serviceReportRepository;

    public List<ServiceReport> getAllServiceReports() {
        return serviceReportRepository.findAll();
    }

    public Optional<ServiceReport> getServiceReportById(Integer id) {
        return serviceReportRepository.findById(id);
    }

    public ServiceReport createServiceReport(ServiceReport serviceReport) {
        return serviceReportRepository.save(serviceReport);
    }

    public Optional<ServiceReport> updateServiceReport(Integer id, ServiceReport serviceReportDetails) {
        return serviceReportRepository.findById(id).map(serviceReport -> {
            serviceReport.setReportForm(serviceReportDetails.getReportForm());
            serviceReport.setTeam(serviceReportDetails.getTeam());
            serviceReport.setFavourite(serviceReportDetails.isFavourite());
            serviceReport.setSaved(serviceReportDetails.isSaved());
            serviceReport.setShared(serviceReportDetails.isShared());
            return serviceReportRepository.save(serviceReport);
        });
    }

    public boolean deleteServiceReport(Integer id) {
        return serviceReportRepository.findById(id).map(serviceReport -> {
            serviceReportRepository.delete(serviceReport);
            return true;
        }).orElse(false);
    }
}
