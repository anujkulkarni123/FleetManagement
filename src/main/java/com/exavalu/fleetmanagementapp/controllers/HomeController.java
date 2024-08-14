package com.exavalu.fleetmanagementapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exavalu.fleetmanagementapp.models.Equipment;
import com.exavalu.fleetmanagementapp.models.Role;
import com.exavalu.fleetmanagementapp.models.RoleNameEnum;
import com.exavalu.fleetmanagementapp.models.User;
import com.exavalu.fleetmanagementapp.services.EquipmentService;
import com.exavalu.fleetmanagementapp.services.TeamService;
import com.exavalu.fleetmanagementapp.services.UserService;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;
    
    @Autowired
    private EquipmentService equipmentService;
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        
        // Create a new user object
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPageItems("pageItems"); // not sure what to put here
        user.setVolumeUnit("testVolumeUnit");
        
        // by default set role to user; not admin
        Role role = new Role();
        role.setRoleId(Long.valueOf(2));
        role.setRoleName(RoleNameEnum.User);
        user.setRole(role);
        
        user.setTeam(teamService.getTeamById(1));
        user.setVerificationCode("0000");
        Optional<Equipment> equipment = equipmentService.getEquipmentById(1);
        user.setWatchingEquipment(equipment.get());
        
        // Encrypt the password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        
        // Save the user to the database
        userService.createUser(user);
        
        // Redirect to login page after successful registration
        return "redirect:/login";
    }
}
