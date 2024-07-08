package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.BillingInfo;
import com.exavalu.fleetmanagementapp.services.BillingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billinginfo")
public class BillingInfoController {

    @Autowired
    private BillingInfoService billingInfoService;

    // Get all BillingInfo
    @GetMapping
    public List<BillingInfo> getAllBillingInfo() {
        return billingInfoService.getAllBillingInfo();
    }

    // Get BillingInfo by ID
    @GetMapping("/{id}")
    public ResponseEntity<BillingInfo> getBillingInfoById(@PathVariable Integer id) {
        return billingInfoService.getBillingInfoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new BillingInfo
    @PostMapping
    public BillingInfo createBillingInfo(@RequestBody BillingInfo billingInfo) {
        return billingInfoService.createBillingInfo(billingInfo);
    }

    // Update existing BillingInfo
    @PutMapping("/{id}")
    public ResponseEntity<BillingInfo> updateBillingInfo(@PathVariable Integer id, @RequestBody BillingInfo billingInfoDetails) {
        return billingInfoService.updateBillingInfo(id, billingInfoDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete BillingInfo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBillingInfo(@PathVariable Integer id) {
        if (billingInfoService.deleteBillingInfo(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
