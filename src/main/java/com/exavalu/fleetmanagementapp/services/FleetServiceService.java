package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.FleetService;
import com.exavalu.fleetmanagementapp.repositories.FleetServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FleetServiceService {

    @Autowired
    private FleetServiceRepository serviceRepository;

    public List<FleetService> getAllServices() {
        return serviceRepository.findAll();
    }

    public Optional<FleetService> getServiceById(Integer id) {
        return serviceRepository.findById(id);
    }

    public FleetService createService(FleetService service) {
        return serviceRepository.save(service);
    }

    public Optional<FleetService> updateService(Integer id, FleetService serviceDetails) {
        return serviceRepository.findById(id).map(service -> {
            service.setTeam(serviceDetails.getTeam());
            service.setVehicle(serviceDetails.getVehicle());
            service.setCompletionDate(serviceDetails.getCompletionDate());
            service.setWatchers(serviceDetails.getWatchers());
            service.setPriorityClass(serviceDetails.getPriorityClass());
            service.setMeter(serviceDetails.getMeter());
            service.setServiceTasks(serviceDetails.getServiceTasks());
            service.setIssues(serviceDetails.getIssues());
            service.setVendor(serviceDetails.getVendor());
            service.setTotalPrice(serviceDetails.getTotalPrice());
            service.setWorkOrder(serviceDetails.getWorkOrder());
            service.setLabels(serviceDetails.getLabels());
            return serviceRepository.save(service);
        });
    }

    public boolean deleteService(Integer id) {
        return serviceRepository.findById(id).map(service -> {
            serviceRepository.delete(service);
            return true;
        }).orElse(false);
    }
}
