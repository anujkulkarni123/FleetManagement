package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.VehicleAssignments;
import com.exavalu.fleetmanagementapp.repositories.VehicleAssignmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleAssignmentsService {

    @Autowired
    private VehicleAssignmentsRepository vehicleAssignmentsRepository;

    public List<VehicleAssignments> getAllVehicleAssignments() {
        return vehicleAssignmentsRepository.findAll();
    }

    public Optional<VehicleAssignments> getVehicleAssignmentById(Integer id) {
        return vehicleAssignmentsRepository.findById(id);
    }

    public VehicleAssignments createVehicleAssignment(VehicleAssignments vehicleAssignment) {
        return vehicleAssignmentsRepository.save(vehicleAssignment);
    }

    public Optional<VehicleAssignments> updateVehicleAssignment(Integer id, VehicleAssignments vehicleAssignmentDetails) {
        return vehicleAssignmentsRepository.findById(id).map(vehicleAssignment -> {
            vehicleAssignment.setVehicle(vehicleAssignmentDetails.getVehicle());
            vehicleAssignment.setStartTime(vehicleAssignmentDetails.getStartTime());
            vehicleAssignment.setEndTime(vehicleAssignmentDetails.getEndTime());
            vehicleAssignment.setDuration(vehicleAssignmentDetails.getDuration());
            vehicleAssignment.setOperator(vehicleAssignmentDetails.getOperator());
            vehicleAssignment.setUsageField(vehicleAssignmentDetails.getUsageField());
            vehicleAssignment.setStartingOdometer(vehicleAssignmentDetails.getStartingOdometer());
            vehicleAssignment.setEndingOdometer(vehicleAssignmentDetails.getEndingOdometer());
            return vehicleAssignmentsRepository.save(vehicleAssignment);
        });
    }

    public boolean deleteVehicleAssignment(Integer id) {
        return vehicleAssignmentsRepository.findById(id).map(vehicleAssignment -> {
            vehicleAssignmentsRepository.delete(vehicleAssignment);
            return true;
        }).orElse(false);
    }
}
