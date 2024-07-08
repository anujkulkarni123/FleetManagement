package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.Inspections;
import com.exavalu.fleetmanagementapp.repositories.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InspectionService {

    @Autowired
    private InspectionRepository inspectionRepository;

    public List<Inspections> getAllInspections() {
        return inspectionRepository.findAll();
    }

    public Optional<Inspections> getInspectionById(Integer id) {
        return inspectionRepository.findById(id);
    }

    public Inspections createInspection(Inspections inspection) {
        return inspectionRepository.save(inspection);
    }

    public Optional<Inspections> updateInspection(Integer id, Inspections inspectionDetails) {
        return inspectionRepository.findById(id).map(inspection -> {
            inspection.setVehicle(inspectionDetails.getVehicle());
            inspection.setVehicleGroup(inspectionDetails.getVehicleGroup());
            inspection.setSubmittedDate(inspectionDetails.getSubmittedDate());
            inspection.setDuration(inspectionDetails.getDuration());
            inspection.setInspectionForm(inspectionDetails.getInspectionForm());
            inspection.setUser(inspectionDetails.getUser());
            inspection.setLocationException(inspectionDetails.getLocationException());
            inspection.setFailedItems(inspectionDetails.getFailedItems());
            inspection.setTeam(inspectionDetails.getTeam());
            return inspectionRepository.save(inspection);
        });
    }

    public boolean deleteInspection(Integer id) {
        return inspectionRepository.findById(id).map(inspection -> {
            inspectionRepository.delete(inspection);
            return true;
        }).orElse(false);
    }
}
