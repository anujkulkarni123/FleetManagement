package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.Fault;
import com.exavalu.fleetmanagementapp.repositories.FaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaultService {

    @Autowired
    private FaultRepository faultRepository;

    public List<Fault> getAllFaults() {
        return faultRepository.findAll();
    }

    public Optional<Fault> getFaultById(Integer id) {
        return faultRepository.findById(id);
    }

    public Fault createFault(Fault fault) {
        return faultRepository.save(fault);
    }

    public Optional<Fault> updateFault(Integer id, Fault faultDetails) {
        return faultRepository.findById(id).map(fault -> {
            fault.setVehicle(faultDetails.getVehicle());
            fault.setCode(faultDetails.getCode());
            fault.setCritical(faultDetails.isCritical());
            fault.setName(faultDetails.getName());
            fault.setDescription(faultDetails.getDescription());
            fault.setLastOccuredDate(faultDetails.getLastOccuredDate());
            fault.setStatus(faultDetails.getStatus());
            fault.setIssue(faultDetails.getIssue());
            fault.setTeam(faultDetails.getTeam());
            return faultRepository.save(fault);
        });
    }

    public boolean deleteFault(Integer id) {
        return faultRepository.findById(id).map(fault -> {
            faultRepository.delete(fault);
            return true;
        }).orElse(false);
    }
}
