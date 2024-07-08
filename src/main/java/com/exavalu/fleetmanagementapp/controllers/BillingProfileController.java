package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.BillingProfile;
import com.exavalu.fleetmanagementapp.services.BillingProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billingprofile")
public class BillingProfileController {

    @Autowired
    private BillingProfileService billingProfileService;

    @GetMapping
    public List<BillingProfile> getAllBillingProfiles() {
        return billingProfileService.getAllBillingProfiles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillingProfile> getBillingProfileById(@PathVariable Integer id) {
        return billingProfileService.getBillingProfileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BillingProfile createBillingProfile(@RequestBody BillingProfile billingProfile) {
        return billingProfileService.createBillingProfile(billingProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillingProfile> updateBillingProfile(@PathVariable Integer id, @RequestBody BillingProfile billingProfileDetails) {
        return billingProfileService.updateBillingProfile(id, billingProfileDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBillingProfile(@PathVariable Integer id) {
        if (billingProfileService.deleteBillingProfile(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
