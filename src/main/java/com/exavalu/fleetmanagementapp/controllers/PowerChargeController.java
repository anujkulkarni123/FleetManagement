package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.PowerCharge;
import com.exavalu.fleetmanagementapp.services.PowerChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/powerCharges")
public class PowerChargeController {

    @Autowired
    private PowerChargeService powerChargeService;

    // Get all Power Charges
    @GetMapping
    public List<PowerCharge> getAllPowerCharges() {
        return powerChargeService.getAllPowerCharges();
    }

    // Get Power Charge by ID
    @GetMapping("/{id}")
    public ResponseEntity<PowerCharge> getPowerChargeById(@PathVariable Integer id) {
        return powerChargeService.getPowerChargeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Power Charge
    @PostMapping
    public PowerCharge createPowerCharge(@RequestBody PowerCharge powerCharge) {
        return powerChargeService.createPowerCharge(powerCharge);
    }

    // Update existing Power Charge
    @PutMapping("/{id}")
    public ResponseEntity<PowerCharge> updatePowerCharge(@PathVariable Integer id, @RequestBody PowerCharge powerChargeDetails) {
        return powerChargeService.updatePowerCharge(id, powerChargeDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Power Charge
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePowerCharge(@PathVariable Integer id) {
        if (powerChargeService.deletePowerCharge(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
