package com.exavalu.fleetmanagementapp.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exavalu.fleetmanagementapp.models.VehicleReminder;
import com.exavalu.fleetmanagementapp.models.Team;
import com.exavalu.fleetmanagementapp.models.User;
import com.exavalu.fleetmanagementapp.models.Vehicle;
import com.exavalu.fleetmanagementapp.services.VehicleReminderService;
import com.exavalu.fleetmanagementapp.services.TeamService;
import com.exavalu.fleetmanagementapp.services.UserService;
import com.exavalu.fleetmanagementapp.services.VehicleService;

@Controller
@RequestMapping("/vehicle-reminders")
public class VehicleReminderController {

    @Autowired
    private VehicleReminderService vehicleReminderService;

    @Autowired
    private TeamService teamService;

    @Autowired 
    private VehicleService vehicleService;

    @Autowired 
    private UserService userService;

    @GetMapping
    public String getAllVehicleReminders(Model model) {
        List<VehicleReminder> reminders = vehicleReminderService.getAllVehicleReminders();
        Team team = teamService.getTeamById(1);
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        List<User> users = userService.getAllUsers();
        model.addAttribute("team", team);
        model.addAttribute("reminders", reminders);
        model.addAttribute("vehicleList", vehicles);
        model.addAttribute("watcherList", users);
        
        return "vehicleReminder"; // This should match the name of the HTML file (vehicleReminder.html)
    }

    // Get VehicleReminder by ID
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<VehicleReminder> getVehicleReminderById(@PathVariable Integer id) {
        return vehicleReminderService.getVehicleReminderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new VehicleReminder
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<VehicleReminder> createVehicleReminder(@RequestParam("teamId") Integer teamId, 
            @RequestParam("vehicleId") Integer vehicleId,
            @RequestParam("status") String status, 
            @RequestParam("nextDueDate") LocalDate nextDueDate,
            @RequestParam(value = "renewalType", required = false) String renewalType,
            @RequestParam(value = "watcherId", required = false) Integer watcherId) {
        
        VehicleReminder vehicleReminder = new VehicleReminder();
        
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        Team team = teamService.getTeamById(teamId);
        User watcher = watcherId != null ? userService.getUserById(watcherId) : null;
        
        vehicleReminder.setTeam(team);
        vehicleReminder.setVehicle(vehicle);
        vehicleReminder.setStatus(status);
        vehicleReminder.setNextDueDate(nextDueDate);
        vehicleReminder.setRenewalType(renewalType);
        vehicleReminder.setWatchers(watcher);
        
        vehicleReminderService.createVehicleReminder(vehicleReminder);

        return ResponseEntity.ok(vehicleReminder);
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<VehicleReminder> updateVehicleReminder(@PathVariable Integer id, 
                                        @RequestParam Integer teamId,
                                        @RequestParam Integer vehicleId, 
                                        @RequestParam String status, 
                                        @RequestParam String nextDueDate,
                                        @RequestParam(value = "renewalType", required = false) String renewalType,
                                        @RequestParam(value = "watcherId", required = false) Integer watcherId) {
    	
    	System.out.println(id);
        return vehicleReminderService.getVehicleReminderById(id)
                .map(vehicleReminder -> {
                    Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
                    Team team = teamService.getTeamById(teamId);
                    User watcher = watcherId != null ? userService.getUserById(watcherId) : null;
                    
                    vehicleReminder.setTeam(team);
                    vehicleReminder.setVehicle(vehicle);
                    vehicleReminder.setStatus(status);
                    vehicleReminder.setNextDueDate(LocalDate.parse(nextDueDate));
                    vehicleReminder.setRenewalType(renewalType);
                    vehicleReminder.setWatchers(watcher);
                    
                    vehicleReminderService.updateVehicleReminder(vehicleReminder);
                    return ResponseEntity.ok(vehicleReminder);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete Vehicle Reminder
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteVehicleReminder(@PathVariable Integer id) {
        if (vehicleReminderService.deleteVehicleReminder(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Filter Vehicle Reminders
    @PostMapping("/filter")
    @ResponseBody
    public List<VehicleReminder> getFilteredVehicleReminders(@RequestBody Map<String, List<Integer>> filterCriteria) {
        List<Integer> vehicleIds = filterCriteria.get("vehicles");

        List<VehicleReminder> allReminders = vehicleReminderService.getAllVehicleReminders();

        // Manual filtering with handling for empty filter criteria
        List<VehicleReminder> filteredReminders = allReminders.stream()
                .filter(reminder -> (vehicleIds.isEmpty() || vehicleIds.contains(reminder.getVehicle().getVehicleId())))
                .collect(Collectors.toList());

        // Extracting and printing the filtered reminder IDs
        List<Integer> filteredReminderIds = filteredReminders.stream()
                .map(VehicleReminder::getId)
                .collect(Collectors.toList());

        System.out.println("Filtered Vehicle Reminder IDs: " + filteredReminderIds);

        return filteredReminders;
    }
}
