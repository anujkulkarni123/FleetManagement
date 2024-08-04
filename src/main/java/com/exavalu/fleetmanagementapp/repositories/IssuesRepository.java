package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.Issues;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuesRepository extends JpaRepository<Issues, Integer> {
}
