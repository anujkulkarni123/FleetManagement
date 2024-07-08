package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.FuelCharge;
import com.exavalu.fleetmanagementapp.services.FuelChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuelCharges")
public class FuelChargeController {

    @Autowired
    private FuelChargeService fuelChargeService;

    // Get all Fuel Charges
    @GetMapping
    public List<FuelCharge> getAllFuelCharges() {
        return fuelChargeService.getAllFuelCharges();
    }

    // Get Fuel Charge by ID
    @GetMapping("/{id}")
    public ResponseEntity<FuelCharge> getFuelChargeById(@PathVariable Integer id) {
        return fuelChargeService.getFuelChargeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Fuel Charge
    @PostMapping
    public FuelCharge createFuelCharge(@RequestBody FuelCharge fuelCharge) {
        return fuelChargeService.createFuelCharge(fuelCharge);
    }

    // Update existing Fuel Charge
    @PutMapping("/{id}")
    public ResponseEntity<FuelCharge> updateFuelCharge(@PathVariable Integer id, @RequestBody FuelCharge fuelChargeDetails) {
        return fuelChargeService.updateFuelCharge(id, fuelChargeDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Fuel Charge
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuelCharge(@PathVariable Integer id) {
        if (fuelChargeService.deleteFuelCharge(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
