package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.ServiceTask;
import com.exavalu.fleetmanagementapp.repositories.ServiceTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTaskService {

    @Autowired
    private ServiceTaskRepository serviceTaskRepository;

    public List<ServiceTask> getAllServiceTasks() {
        return serviceTaskRepository.findAll();
    }

    public Optional<ServiceTask> getServiceTaskById(Integer id) {
        return serviceTaskRepository.findById(id);
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
            serviceTask.setService(serviceTaskDetails.getService());
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
