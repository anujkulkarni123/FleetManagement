package com.exavalu.fleetmanagementapp.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exavalu.fleetmanagementapp.models.ServiceTask;
import com.exavalu.fleetmanagementapp.repositories.ServiceTaskRepository;

@Service
public class ServiceTaskService {

    @Autowired
    private ServiceTaskRepository serviceTaskRepository;

    public List<ServiceTask> getAllServiceTasks() {
        return serviceTaskRepository.findAll();
    }

    public ServiceTask getServiceTaskById(Integer id) {
        try {
            return serviceTaskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("ServiceTask not found"));
        } catch (NoSuchElementException e) {
            // Handle the exception, e.g., log it or rethrow it
            throw e;
        }
    }

    public ServiceTask createServiceTask(ServiceTask serviceTask) {
        return serviceTaskRepository.save(serviceTask);
    }

    public Optional<ServiceTask> updateServiceTask(Integer id, ServiceTask serviceTaskDetails) {
        return serviceTaskRepository.findById(id).map(serviceTask -> {
            serviceTask.setName(serviceTaskDetails.getName());
            serviceTask.setDescription(serviceTaskDetails.getDescription());
            serviceTask.setServiceEntries(serviceTaskDetails.getServiceEntries());
            serviceTask.setServiceReminders(serviceTaskDetails.getServiceReminders());
            serviceTask.setServicePrograms(serviceTaskDetails.getServicePrograms());
            serviceTask.setWorkOrders(serviceTaskDetails.getWorkOrders());
            serviceTask.setDefaultRepairReason(serviceTaskDetails.getDefaultRepairReason());
            serviceTask.setDefaultCategoryCode(serviceTaskDetails.getDefaultCategoryCode());
            serviceTask.setDefaultSystemCode(serviceTaskDetails.getDefaultSystemCode());
            serviceTask.setDefaultAssemblyCode(serviceTaskDetails.getDefaultAssemblyCode());
            serviceTask.setWorkOrder(serviceTaskDetails.getWorkOrder());
            return serviceTaskRepository.save(serviceTask);
        });
    }

    public boolean deleteServiceTask(Integer id) {
        return serviceTaskRepository.findById(id).map(serviceTask -> {
            serviceTaskRepository.delete(serviceTask);
            return true;
        }).orElse(false);
    }
}
