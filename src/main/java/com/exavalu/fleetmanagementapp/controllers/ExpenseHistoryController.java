package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.ExpenseHistory;
import com.exavalu.fleetmanagementapp.services.ExpenseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense-history")
public class ExpenseHistoryController {

    @Autowired
    private ExpenseHistoryService expenseHistoryService;

    // Get all ExpenseHistory records
    @GetMapping
    public List<ExpenseHistory> getAllExpenseHistories() {
        return expenseHistoryService.getAllExpenseHistories();
    }

    // Get ExpenseHistory by ID
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseHistory> getExpenseHistoryById(@PathVariable Integer id) {
        return expenseHistoryService.getExpenseHistoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new ExpenseHistory
    @PostMapping
    public ExpenseHistory createExpenseHistory(@RequestBody ExpenseHistory expenseHistory) {
        return expenseHistoryService.createExpenseHistory(expenseHistory);
    }

    // Update existing ExpenseHistory
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseHistory> updateExpenseHistory(@PathVariable Integer id, @RequestBody ExpenseHistory expenseHistoryDetails) {
        return expenseHistoryService.updateExpenseHistory(id, expenseHistoryDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete ExpenseHistory
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpenseHistory(@PathVariable Integer id) {
        if (expenseHistoryService.deleteExpenseHistory(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
