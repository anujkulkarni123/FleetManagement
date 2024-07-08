package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.MeterHistory;
import com.exavalu.fleetmanagementapp.services.MeterHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meter-history")
public class MeterHistoryController {

    @Autowired
    private MeterHistoryService meterHistoryService;

    // Get all MeterHistories
    @GetMapping
    public List<MeterHistory> getAllMeterHistories() {
        return meterHistoryService.getAllMeterHistories();
    }

    // Get MeterHistory by ID
    @GetMapping("/{id}")
    public ResponseEntity<MeterHistory> getMeterHistoryById(@PathVariable Integer id) {
        return meterHistoryService.getMeterHistoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new MeterHistory
    @PostMapping
    public MeterHistory createMeterHistory(@RequestBody MeterHistory meterHistory) {
        return meterHistoryService.createMeterHistory(meterHistory);
    }

    // Update existing MeterHistory
    @PutMapping("/{id}")
    public ResponseEntity<MeterHistory> updateMeterHistory(@PathVariable Integer id, @RequestBody MeterHistory meterHistoryDetails) {
        return meterHistoryService.updateMeterHistory(id, meterHistoryDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete MeterHistory
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeterHistory(@PathVariable Integer id) {
        if (meterHistoryService.deleteMeterHistory(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
