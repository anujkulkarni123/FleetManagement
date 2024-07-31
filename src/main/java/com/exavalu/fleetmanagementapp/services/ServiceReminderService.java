package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.ServiceReminder;
import com.exavalu.fleetmanagementapp.repositories.ServiceReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceReminderService {

    @Autowired
    private ServiceReminderRepository serviceReminderRepository;

    public List<ServiceReminder> getAllServiceReminders() {
        return serviceReminderRepository.findAll();
    }

    public Optional<ServiceReminder> getServiceReminderById(Integer id) {
        return serviceReminderRepository.findById(id);
    }

    public ServiceReminder createServiceReminder(ServiceReminder serviceReminder) {
        return serviceReminderRepository.save(serviceReminder);
    }
    
    public Optional<ServiceReminder> updateServiceReminder(ServiceReminder serviceReminderDetails) {
        return serviceReminderRepository.findById(serviceReminderDetails.getId()).map(serviceReminder -> {
            serviceReminder.setActiveWorkOrder(serviceReminderDetails.getActiveWorkOrder());
            serviceReminder.setLastCompletedDate(serviceReminderDetails.getLastCompletedDate());
            serviceReminder.setCompliance(serviceReminderDetails.getCompliance());
            serviceReminder.setWatchers(serviceReminderDetails.getWatchers()); // Assuming a single watcher
            serviceReminder.setTeam(serviceReminderDetails.getTeam());
            serviceReminder.setVehicle(serviceReminderDetails.getVehicle());
            serviceReminder.setStatus(serviceReminderDetails.getStatus());
            serviceReminder.setNextDueDate(serviceReminderDetails.getNextDueDate());
            serviceReminder.setServiceTask(serviceReminderDetails.getServiceTask());
            return serviceReminderRepository.save(serviceReminder);
        });
    }



    public boolean deleteServiceReminder(Integer id) {
        return serviceReminderRepository.findById(id).map(serviceReminder -> {
            serviceReminderRepository.delete(serviceReminder);
            return true;
        }).orElse(false);
    }
    
  

    
}


