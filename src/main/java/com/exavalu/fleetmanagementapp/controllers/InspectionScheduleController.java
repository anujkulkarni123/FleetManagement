package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.InspectionSchedule;
import com.exavalu.fleetmanagementapp.services.InspectionScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inspection-schedules")
public class InspectionScheduleController {

    @Autowired
    private InspectionScheduleService inspectionScheduleService;

    // Get all Inspection Schedules
    @GetMapping
    public List<InspectionSchedule> getAllInspectionSchedules() {
        return inspectionScheduleService.getAllInspectionSchedules();
    }

    // Get Inspection Schedule by ID
    @GetMapping("/{id}")
    public ResponseEntity<InspectionSchedule> getInspectionScheduleById(@PathVariable Integer id) {
        return inspectionScheduleService.getInspectionScheduleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Inspection Schedule
    @PostMapping
    public InspectionSchedule createInspectionSchedule(@RequestBody InspectionSchedule inspectionSchedule) {
        return inspectionScheduleService.createInspectionSchedule(inspectionSchedule);
    }

    // Update existing Inspection Schedule
    @PutMapping("/{id}")
    public ResponseEntity<InspectionSchedule> updateInspectionSchedule(@PathVariable Integer id, @RequestBody InspectionSchedule inspectionScheduleDetails) {
        return inspectionScheduleService.updateInspectionSchedule(id, inspectionScheduleDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Inspection Schedule
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspectionSchedule(@PathVariable Integer id) {
        if (inspectionScheduleService.deleteInspectionSchedule(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
