package com.exavalu.fleetmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.exavalu.fleetmanagementapp.models.ExpenseHistory;

@Repository
public interface ExpenseHistoryRepository extends JpaRepository<ExpenseHistory, Integer> {
}
