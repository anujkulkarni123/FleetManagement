package com.exavalu.fleetmanagementapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exavalu.fleetmanagementapp.models.ServiceTask;
import com.exavalu.fleetmanagementapp.services.ServiceTaskService;

@RestController
@RequestMapping("/api/service-tasks")
public class ServiceTaskController {

    @Autowired
    private ServiceTaskService serviceTaskService;

    // Get all Service Tasks
    @GetMapping
    public List<ServiceTask> getAllServiceTasks() {
        return serviceTaskService.getAllServiceTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceTask> getServiceTaskById(@PathVariable Integer id) {
        Optional<ServiceTask> serviceTaskOptional = Optional.ofNullable(serviceTaskService.getServiceTaskById(id));
        System.out.println("Service Task found: " + serviceTaskOptional.isPresent());
        return serviceTaskOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Service Task
    @PostMapping
    public ServiceTask createServiceTask(@RequestBody ServiceTask serviceTask) {
        return serviceTaskService.createServiceTask(serviceTask);
    }

    // Update existing Service Task
    @PutMapping("/{id}")
    public ResponseEntity<ServiceTask> updateServiceTask(@PathVariable Integer id, @RequestBody ServiceTask serviceTaskDetails) {
        return serviceTaskService.updateServiceTask(id, serviceTaskDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Service Task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceTask(@PathVariable Integer id) {
        if (serviceTaskService.deleteServiceTask(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
