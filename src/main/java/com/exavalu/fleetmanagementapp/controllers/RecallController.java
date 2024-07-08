package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.Recall;
import com.exavalu.fleetmanagementapp.services.RecallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recalls")
public class RecallController {

    @Autowired
    private RecallService recallService;

    // Get all Recalls
    @GetMapping
    public List<Recall> getAllRecalls() {
        return recallService.getAllRecalls();
    }

    // Get Recall by ID
    @GetMapping("/{id}")
    public ResponseEntity<Recall> getRecallById(@PathVariable Integer id) {
        return recallService.getRecallById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Recall
    @PostMapping
    public Recall createRecall(@RequestBody Recall recall) {
        return recallService.createRecall(recall);
    }

    // Update existing Recall
    @PutMapping("/{id}")
    public ResponseEntity<Recall> updateRecall(@PathVariable Integer id, @RequestBody Recall recallDetails) {
        return recallService.updateRecall(id, recallDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Recall
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecall(@PathVariable Integer id) {
        if (recallService.deleteRecall(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
