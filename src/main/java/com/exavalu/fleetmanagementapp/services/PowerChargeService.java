package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.PowerCharge;
import com.exavalu.fleetmanagementapp.repositories.PowerChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PowerChargeService {

    @Autowired
    private PowerChargeRepository powerChargeRepository;

    public List<PowerCharge> getAllPowerCharges() {
        return powerChargeRepository.findAll();
    }

    public Optional<PowerCharge> getPowerChargeById(Integer id) {
        return powerChargeRepository.findById(id);
    }

    public PowerCharge createPowerCharge(PowerCharge powerCharge) {
        return powerChargeRepository.save(powerCharge);
    }

    public Optional<PowerCharge> updatePowerCharge(Integer id, PowerCharge powerChargeDetails) {
        return powerChargeRepository.findById(id).map(powerCharge -> {
            powerCharge.setTeam(powerChargeDetails.getTeam());
            powerCharge.setVehicle(powerChargeDetails.getVehicle());
            powerCharge.setStartTime(powerChargeDetails.getStartTime());
            powerCharge.setEndTime(powerChargeDetails.getEndTime());
            powerCharge.setVendor(powerChargeDetails.getVendor());
            powerCharge.setMeterEntry(powerChargeDetails.getMeterEntry());
            powerCharge.setUsage(powerChargeDetails.getUsageField());
            powerCharge.setEconomy(powerChargeDetails.getEconomy());
            powerCharge.setTotalEnergy(powerChargeDetails.getTotalEnergy());
            powerCharge.setUnitPrice(powerChargeDetails.getUnitPrice());
            powerCharge.setEnergyCost(powerChargeDetails.getEnergyCost());
            return powerChargeRepository.save(powerCharge);
        });
    }

    public boolean deletePowerCharge(Integer id) {
        return powerChargeRepository.findById(id).map(powerCharge -> {
            powerChargeRepository.delete(powerCharge);
            return true;
        }).orElse(false);
    }
}
