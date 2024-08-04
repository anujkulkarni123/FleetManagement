package com.exavalu.fleetmanagementapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.exavalu.fleetmanagementapp.models.Comment;
import com.exavalu.fleetmanagementapp.models.Issues;
import com.exavalu.fleetmanagementapp.models.User;
import com.exavalu.fleetmanagementapp.models.Vehicle;
import com.exavalu.fleetmanagementapp.services.CommentService;
import com.exavalu.fleetmanagementapp.services.IssuesService;
import com.exavalu.fleetmanagementapp.services.UserService;
import com.exavalu.fleetmanagementapp.services.VehicleService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class DashboardController {
	// It will probably need a way to get all of the information in the dashboard
	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@Autowired
	private IssuesService issuesService;

	@GetMapping("/")
	public String findAll(Model model) throws JsonProcessingException {
		// Need to give it access to our list of vehicles
		List<Vehicle> vehicles = vehicleService.getAllVehicles();
		model.addAttribute("vehicles", vehicles);

		// Need to give access to comments and all other aggregate data
		List<Comment> comments = commentService.getAllRecentComments();
		model.addAttribute("comments", comments);

		// Need to grab all users so we can filter by their information
		List<User> users = userService.getAllUsers();
		System.out.println(users.size());
		model.addAttribute("users", users);

		// late issues
		List<Issues> lateIssues = issuesService.getAllIntervalIssues(7);
		System.out.println(lateIssues.size());
		model.addAttribute("lateIssues", lateIssues);
		model.addAttribute("numLateIssues", lateIssues.size());

		// due soon issues
		List<Issues> dueSoonIssues = issuesService.getAllIntervalIssues(2);
		System.out.println(lateIssues.size());
		model.addAttribute("dueSoonIssues", dueSoonIssues);
		model.addAttribute("numDueSoonIssues", dueSoonIssues.size());

		// Grab vehicle report data
		String data = vehicleService.getVehicleReportJsonData();
		model.addAttribute("data", data);
		return "index";
	}

	// Specifies a particular user
	@GetMapping("/user{id:\\d+}")
	public String findAllWithUserId(@PathVariable("id") Integer id, Model model) throws JsonProcessingException {
		// Get list of vehicles that this user has access to
		List<Vehicle> vehicles = vehicleService.getAllVehiclesWithUser(id);
		model.addAttribute("vehicles", vehicles);

		String data = vehicleService.getVehicleReportJsonData(vehicles);
		model.addAttribute("data", data);

		// Get all recent comments from a user
		List<Comment> comments = commentService.getAllCommentsFromUser(id);
		model.addAttribute("comments", comments);
		// Need to grab all users so we can filter by their information
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);

		// late issues
		List<Issues> lateIssues = issuesService.getAllIntervalIssues(7);
		System.out.println(lateIssues.size());
		model.addAttribute("lateIssues", lateIssues);
		model.addAttribute("numLateIssues", lateIssues.size());

		// due soon issues
		List<Issues> dueSoonIssues = issuesService.getAllIntervalIssues(2);
		System.out.println(lateIssues.size());
		model.addAttribute("dueSoonIssues", dueSoonIssues);
		model.addAttribute("numDueSoonIssues", dueSoonIssues.size());
		return "index";
	}

	// Specifies a particular group to filter by
	@GetMapping("/{group}")
	public String findAllWithGroup(@PathVariable("group") String group, Model model) throws JsonProcessingException {
		// Get list of vehicles that this group has access to
		List<Vehicle> vehicles = vehicleService.getAllVehiclesWithGroup(group);
		model.addAttribute("vehicles", vehicles);

		// Need to give access to comments and all other aggregate data
		List<Comment> comments = commentService.getAllRecentComments();
		model.addAttribute("comments", comments);

		// Need to grab all users so we can filter by their information
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);

		String data = vehicleService.getVehicleReportJsonData(vehicles);
		model.addAttribute("data", data);

		// late issues
		List<Issues> lateIssues = issuesService.getAllIntervalIssues(7);
		System.out.println(lateIssues.size());
		model.addAttribute("lateIssues", lateIssues);
		model.addAttribute("numLateIssues", lateIssues.size());

		// due soon issues
		List<Issues> dueSoonIssues = issuesService.getAllIntervalIssues(2);
		System.out.println(lateIssues.size());
		model.addAttribute("dueSoonIssues", dueSoonIssues);
		model.addAttribute("numDueSoonIssues", dueSoonIssues.size());
		return "index";
	}
}