package com.exavalu.fleetmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exavalu.fleetmanagementapp.models.Role;
 

 
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
 
    @Query("SELECT r.roleName FROM Role r WHERE r.roleId = (SELECT u.userRoleId FROM User u WHERE u.userId = ?1)")
    String findRoleNameByUserId(int userId);
}