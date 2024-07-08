package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.ServiceTask;
import com.exavalu.fleetmanagementapp.services.ServiceTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Get Service Task by ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceTask> getServiceTaskById(@PathVariable Integer id) {
        return serviceTaskService.getServiceTaskById(id)
                .map(ResponseEntity::ok)
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
