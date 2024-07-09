package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.Forms;
import com.exavalu.fleetmanagementapp.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forms")
public class FormController {

    @Autowired
    private FormService formsService;

    // Get all Forms
    @GetMapping
    public List<Forms> getAllForms() {
        return formsService.getAllForms();
    }

    // Get Form by ID
    @GetMapping("/{id}")
    public ResponseEntity<Forms> getFormById(@PathVariable Integer id) {
        return formsService.getFormById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new Form
    @PostMapping
    public Forms createForm(@RequestBody Forms form) {
        return formsService.createForm(form);
    }

    // Update existing Form
    @PutMapping("/{id}")
    public ResponseEntity<Forms> updateForm(@PathVariable Integer id, @RequestBody Forms formDetails) {
        return formsService.updateForm(id, formDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Form
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable Integer id) {
        if (formsService.deleteForm(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
