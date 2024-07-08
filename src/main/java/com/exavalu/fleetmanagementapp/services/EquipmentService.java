package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.Equipment;
import com.exavalu.fleetmanagementapp.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Optional<Equipment> getEquipmentById(Integer id) {
        return equipmentRepository.findById(id);
    }

    public Equipment createEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Optional<Equipment> updateEquipment(Integer id, Equipment equipmentDetails) {
        return equipmentRepository.findById(id).map(equipment -> {
            equipment.setName(equipmentDetails.getName());
            equipment.setType(equipmentDetails.getType());
            equipment.setBrand(equipmentDetails.getBrand());
            equipment.setModel(equipmentDetails.getModel());
            equipment.setSerialNumber(equipmentDetails.getSerialNumber());
            equipment.setGroupField(equipmentDetails.getGroupField());
            equipment.setStatus(equipmentDetails.getStatus());
            equipment.setCurrentAssignee(equipmentDetails.getCurrentAssignee());
            equipment.setLabels(equipmentDetails.getLabels());
            equipment.setLinkedVehicle(equipmentDetails.getLinkedVehicle());
            equipment.setTeam(equipmentDetails.getTeam());
            equipment.setWatchers(equipmentDetails.getWatchers());
            return equipmentRepository.save(equipment);
        });
    }

    public boolean deleteEquipment(Integer id) {
        return equipmentRepository.findById(id).map(equipment -> {
            equipmentRepository.delete(equipment);
            return true;
        }).orElse(false);
    }
}
