package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.FuelCharge;
import com.exavalu.fleetmanagementapp.repositories.FuelChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuelChargeService {

    @Autowired
    private FuelChargeRepository fuelChargeRepository;

    public List<FuelCharge> getAllFuelCharges() {
        return fuelChargeRepository.findAll();
    }

    public Optional<FuelCharge> getFuelChargeById(Integer id) {
        return fuelChargeRepository.findById(id);
    }

    public FuelCharge createFuelCharge(FuelCharge fuelCharge) {
        return fuelChargeRepository.save(fuelCharge);
    }

    public Optional<FuelCharge> updateFuelCharge(Integer id, FuelCharge fuelChargeDetails) {
        return fuelChargeRepository.findById(id).map(fuelCharge -> {
            fuelCharge.setTeam(fuelChargeDetails.getTeam());
            fuelCharge.setVehicle(fuelChargeDetails.getVehicle());
            fuelCharge.setDate(fuelChargeDetails.getDate());
            fuelCharge.setVendor(fuelChargeDetails.getVendor());
            fuelCharge.setMeter(fuelChargeDetails.getMeter());
            fuelCharge.setUsageField(fuelChargeDetails.getUsageField());
            fuelCharge.setVolume(fuelChargeDetails.getVolume());
            fuelCharge.setTotalCost(fuelChargeDetails.getTotalCost());
            fuelCharge.setAlerts(fuelChargeDetails.getAlerts());
            fuelCharge.setCapacityExceptionVolume(fuelChargeDetails.getCapacityExceptionVolume());
            fuelCharge.setLocationExceptionDistance(fuelChargeDetails.getLocationExceptionDistance());
            return fuelChargeRepository.save(fuelCharge);
        });
    }

    public boolean deleteFuelCharge(Integer id) {
        return fuelChargeRepository.findById(id).map(fuelCharge -> {
            fuelChargeRepository.delete(fuelCharge);
            return true;
        }).orElse(false);
    }
}
