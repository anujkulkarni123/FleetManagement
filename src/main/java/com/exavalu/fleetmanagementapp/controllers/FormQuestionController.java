package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.FormQuestion;
import com.exavalu.fleetmanagementapp.services.FormQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formQuestions")
public class FormQuestionController {

    @Autowired
    private FormQuestionService formQuestionService;

    // Get all FormQuestions
    @GetMapping
    public List<FormQuestion> getAllFormQuestions() {
        return formQuestionService.getAllFormQuestions();
    }

    // Get FormQuestion by ID
    @GetMapping("/{id}")
    public ResponseEntity<FormQuestion> getFormQuestionById(@PathVariable Integer id) {
        return formQuestionService.getFormQuestionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new FormQuestion
    @PostMapping
    public FormQuestion createFormQuestion(@RequestBody FormQuestion formQuestion) {
        return formQuestionService.createFormQuestion(formQuestion);
    }

    // Update existing FormQuestion
    @PutMapping("/{id}")
    public ResponseEntity<FormQuestion> updateFormQuestion(@PathVariable Integer id, @RequestBody FormQuestion formQuestionDetails) {
        return formQuestionService.updateFormQuestion(id, formQuestionDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete FormQuestion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormQuestion(@PathVariable Integer id) {
        if (formQuestionService.deleteFormQuestion(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
