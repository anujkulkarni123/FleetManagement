package com.exavalu.fleetmanagementapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.exavalu.fleetmanagementapp.models.Vehicle;
import com.exavalu.fleetmanagementapp.services.VehicleService;

@Controller
public class DashboardController {
	// itll probably need a way to get all of the information in the dashboard
	@Autowired
	private VehicleService vehicleService;
	
	 @GetMapping("/")
	    public String findAll(Model model) {
		 	// need to give it access to our list of vehicles
		 	List<Vehicle> vehicles = vehicleService.getAllVehicles();
		 	model.addAttribute("vehicles", vehicles);
	        return "index";
	    }
}
