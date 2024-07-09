package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.InspectionSchedule;
import com.exavalu.fleetmanagementapp.repositories.InspectionScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InspectionScheduleService {

    @Autowired
    private InspectionScheduleRepository inspectionScheduleRepository;

    public List<InspectionSchedule> getAllInspectionSchedules() {
        return inspectionScheduleRepository.findAll();
    }

    public Optional<InspectionSchedule> getInspectionScheduleById(Integer id) {
        return inspectionScheduleRepository.findById(id);
    }

    public InspectionSchedule createInspectionSchedule(InspectionSchedule inspectionSchedule) {
        return inspectionScheduleRepository.save(inspectionSchedule);
    }

    public Optional<InspectionSchedule> updateInspectionSchedule(Integer id, InspectionSchedule inspectionScheduleDetails) {
        return inspectionScheduleRepository.findById(id).map(inspectionSchedule -> {
            inspectionSchedule.setVehicle(inspectionScheduleDetails.getVehicle());
            inspectionSchedule.setInspectionForm(inspectionScheduleDetails.getInspectionForm());
            inspectionSchedule.setTeam(inspectionScheduleDetails.getTeam());
            inspectionSchedule.setNextDue(inspectionScheduleDetails.getNextDue());
            inspectionSchedule.setLastInspected(inspectionScheduleDetails.getLastInspected());
            inspectionSchedule.setFrequency(inspectionScheduleDetails.getFrequency());
            inspectionSchedule.setManualOverridden(inspectionScheduleDetails.isManualOverridden());
            return inspectionScheduleRepository.save(inspectionSchedule);
        });
    }

    public boolean deleteInspectionSchedule(Integer id) {
        return inspectionScheduleRepository.findById(id).map(inspectionSchedule -> {
            inspectionScheduleRepository.delete(inspectionSchedule);
            return true;
        }).orElse(false);
    }
}
