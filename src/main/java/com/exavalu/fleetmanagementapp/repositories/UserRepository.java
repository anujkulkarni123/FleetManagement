package com.exavalu.fleetmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exavalu.fleetmanagementapp.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
