package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.ItemFailures;
import com.exavalu.fleetmanagementapp.services.ItemFailuresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item-failures")
public class ItemFailuresController {

    @Autowired
    private ItemFailuresService itemFailuresService;

    // Get all ItemFailures
    @GetMapping
    public List<ItemFailures> getAllItemFailures() {
        return itemFailuresService.getAllItemFailures();
    }

    // Get ItemFailures by ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemFailures> getItemFailuresById(@PathVariable Integer id) {
        return itemFailuresService.getItemFailuresById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new ItemFailures
    @PostMapping
    public ItemFailures createItemFailures(@RequestBody ItemFailures itemFailures) {
        return itemFailuresService.createItemFailures(itemFailures);
    }

    // Update existing ItemFailures
    @PutMapping("/{id}")
    public ResponseEntity<ItemFailures> updateItemFailures(@PathVariable Integer id, @RequestBody ItemFailures itemFailuresDetails) {
        return itemFailuresService.updateItemFailures(id, itemFailuresDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete ItemFailures
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemFailures(@PathVariable Integer id) {
        if (itemFailuresService.deleteItemFailures(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
