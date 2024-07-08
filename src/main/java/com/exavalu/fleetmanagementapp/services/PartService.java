package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.Part;
import com.exavalu.fleetmanagementapp.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    public Optional<Part> getPartById(Integer id) {
        return partRepository.findById(id);
    }

    public Part createPart(Part part) {
        return partRepository.save(part);
    }

    public Optional<Part> updatePart(Integer id, Part partDetails) {
        return partRepository.findById(id).map(part -> {
            part.setTeam(partDetails.getTeam());
            part.setPartName(partDetails.getPartName());
            part.setDescription(partDetails.getDescription());
            part.setCategory(partDetails.getCategory());
            part.setManufacturer(partDetails.getManufacturer());
            part.setManufacturerPartNumber(partDetails.getManufacturerPartNumber());
            part.setMeasurementUnit(partDetails.getMeasurementUnit());
            part.setAisleRowBin(partDetails.getAisleRowBin());
            part.setUnitCost(partDetails.getUnitCost());
            return partRepository.save(part);
        });
    }

    public boolean deletePart(Integer id) {
        return partRepository.findById(id).map(part -> {
            partRepository.delete(part);
            return true;
        }).orElse(false);
    }
}
