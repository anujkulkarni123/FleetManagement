package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.ServiceProgram;
import com.exavalu.fleetmanagementapp.repositories.ServiceProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProgramService {

    @Autowired
    private ServiceProgramRepository serviceProgramRepository;

    public List<ServiceProgram> getAllServicePrograms() {
        return serviceProgramRepository.findAll();
    }

    public Optional<ServiceProgram> getServiceProgramById(Integer id) {
        return serviceProgramRepository.findById(id);
    }

    public ServiceProgram createServiceProgram(ServiceProgram serviceProgram) {
        return serviceProgramRepository.save(serviceProgram);
    }

    public Optional<ServiceProgram> updateServiceProgram(Integer id, ServiceProgram serviceProgramDetails) {
        return serviceProgramRepository.findById(id).map(serviceProgram -> {
            serviceProgram.setTeam(serviceProgramDetails.getTeam());
            serviceProgram.setServiceProgram(serviceProgramDetails.getServiceProgram());
            serviceProgram.setVehicles(serviceProgramDetails.getVehicles());
            serviceProgram.setSchedules(serviceProgramDetails.getSchedules());
            serviceProgram.setPrimaryMeter(serviceProgramDetails.getPrimaryMeter());
            serviceProgram.setSecondaryMeter(serviceProgramDetails.getSecondaryMeter());
            return serviceProgramRepository.save(serviceProgram);
        });
    }

    public boolean deleteServiceProgram(Integer id) {
        return serviceProgramRepository.findById(id).map(serviceProgram -> {
            serviceProgramRepository.delete(serviceProgram);
            return true;
        }).orElse(false);
    }
}
