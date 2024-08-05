package com.exavalu.fleetmanagementapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exavalu.fleetmanagementapp.models.Country;
import com.exavalu.fleetmanagementapp.models.Vehicle;
import com.exavalu.fleetmanagementapp.repositories.VehicleRepository;

@Controller
public class VehicleDashboardController {

	@Autowired
	private VehicleRepository vehicleRepository;

	@GetMapping("/addVehicle")
	public String findAll(Model model) {
		return "addVehicleForm";
	}
	
	@GetMapping("/addMultipleVehicles")
	public String addMulitple(Model model) {
		return "addMultipleVehiclesForm";
	}
	@PostMapping("/api/addVehicle")
	public String saveVehicle(@RequestParam("vin") String vin, @RequestParam("vehicleName") String vehicleName, Model model) {
		Vehicle vehicle = new Vehicle();
		vehicle.setVin(vin);
		vehicle.setName(vehicleName);

		vehicleRepository.save(vehicle);

		model.addAttribute("message", "Vehicle added successfully!");
		return "redirect:/addVehicle";
	}
	
	@PostMapping("/api/addMultipleVehicles")
	public String saveMultipleVehicles(@RequestParam("vin") String vin, @RequestParam("vehicleName") String vehicleName, Model model) {
		Vehicle vehicle = new Vehicle();
		vehicle.setVin(vin);
		vehicle.setName(vehicleName);

		vehicleRepository.save(vehicle);

		model.addAttribute("message", "Vehicle added successfully!");
		return "redirect:/addMultipleVehicles";
	}
	
}
