package com.exavalu.fleetmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exavalu.fleetmanagementapp.models.State;


@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

}
