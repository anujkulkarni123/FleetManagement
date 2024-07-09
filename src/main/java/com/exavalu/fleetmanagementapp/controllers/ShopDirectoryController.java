package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.ShopDirectory;
import com.exavalu.fleetmanagementapp.services.ShopDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop-directories")
public class ShopDirectoryController {

    @Autowired
    private ShopDirectoryService shopDirectoryService;

    // Get all Shop Directories
    @GetMapping
    public List<ShopDirectory> getAllShopDirectories() {
        return shopDirectoryService.getAllShopDirectories();
    }

    // Get Shop Directory by ID
    @GetMapping("/{id}")
    public ResponseEntity<ShopDirectory> getShopDirectoryById(@PathVariable Integer id) {
        return shopDirectoryService.getShopDirectoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Shop Directory
    @PostMapping
    public ShopDirectory createShopDirectory(@RequestBody ShopDirectory shopDirectory) {
        return shopDirectoryService.createShopDirectory(shopDirectory);
    }

    // Update existing Shop Directory
    @PutMapping("/{id}")
    public ResponseEntity<ShopDirectory> updateShopDirectory(@PathVariable Integer id, @RequestBody ShopDirectory shopDirectoryDetails) {
        return shopDirectoryService.updateShopDirectory(id, shopDirectoryDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Shop Directory
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShopDirectory(@PathVariable Integer id) {
        if (shopDirectoryService.deleteShopDirectory(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
