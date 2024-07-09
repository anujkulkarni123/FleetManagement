package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.ExpenseHistory;
import com.exavalu.fleetmanagementapp.repositories.ExpenseHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseHistoryService {

    @Autowired
    private ExpenseHistoryRepository expenseHistoryRepository;

    public List<ExpenseHistory> getAllExpenseHistories() {
        return expenseHistoryRepository.findAll();
    }

    public Optional<ExpenseHistory> getExpenseHistoryById(Integer id) {
        return expenseHistoryRepository.findById(id);
    }

    public ExpenseHistory createExpenseHistory(ExpenseHistory expenseHistory) {
        return expenseHistoryRepository.save(expenseHistory);
    }

    public Optional<ExpenseHistory> updateExpenseHistory(Integer id, ExpenseHistory expenseHistoryDetails) {
        return expenseHistoryRepository.findById(id).map(expenseHistory -> {
            expenseHistory.setVehicle(expenseHistoryDetails.getVehicle());
            expenseHistory.setDate(expenseHistoryDetails.getDate());
            expenseHistory.setType(expenseHistoryDetails.getType());
            expenseHistory.setVendor(expenseHistoryDetails.getVendor());
            expenseHistory.setSource(expenseHistoryDetails.getSource());
            expenseHistory.setAmount(expenseHistoryDetails.getAmount());
            expenseHistory.setWatcher(expenseHistoryDetails.getWatcher());
            return expenseHistoryRepository.save(expenseHistory);
        });
    }

    public boolean deleteExpenseHistory(Integer id) {
        return expenseHistoryRepository.findById(id).map(expenseHistory -> {
            expenseHistoryRepository.delete(expenseHistory);
            return true;
        }).orElse(false);
    }
}
