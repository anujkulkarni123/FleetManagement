package com.exavalu.fleetmanagementapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.exavalu.fleetmanagementapp.models.Country;

@Controller
public class DashboardController {

	 @GetMapping("/")
	    public String findAll(Model model) {
	        return "index";
	    }
}
