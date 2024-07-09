package com.exavalu.fleetmanagementapp.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exavalu.fleetmanagementapp.models.ServiceReminder;
import com.exavalu.fleetmanagementapp.models.ServiceTask;
import com.exavalu.fleetmanagementapp.models.Team;
import com.exavalu.fleetmanagementapp.models.User;
import com.exavalu.fleetmanagementapp.models.Vehicle;
import com.exavalu.fleetmanagementapp.services.ServiceReminderService;
import com.exavalu.fleetmanagementapp.services.ServiceTaskService;
import com.exavalu.fleetmanagementapp.services.TeamService;
import com.exavalu.fleetmanagementapp.services.UserService;
import com.exavalu.fleetmanagementapp.services.VehicleService;

@Controller
@RequestMapping("/service-reminders")
public class ServiceReminderController {

    @Autowired
    private ServiceReminderService serviceReminderService;
    
    @Autowired
    private ServiceTaskService serviceTaskService;
    
    @Autowired 
    private TeamService teamService;
    
    @Autowired 
    private VehicleService vehicleService;
    
    @Autowired 
    private UserService userService;

    // Get all Service Reminders
    @GetMapping
    public String getAllServiceReminders(Model model) {
        List<ServiceReminder> reminders = serviceReminderService.getAllServiceReminders();
        List<ServiceTask> serviceTasks = serviceTaskService.getAllServiceTasks();
        Team team = teamService.getTeamById(1);
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        List<User> users = userService.getAllUsers();
        model.addAttribute("team", team);
        model.addAttribute("reminders", reminders);
        model.addAttribute("serviceTaskList", serviceTasks);
        model.addAttribute("watcherList", users);
        model.addAttribute("vehicleList", vehicles);
        
        return "serviceReminder"; // Return the view name (e.g., service-reminders.html)
    }

    // Get Service Reminder by ID
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ServiceReminder> getServiceReminderById(@PathVariable Integer id) {
        return serviceReminderService.getServiceReminderById(id)
                .map(reminder -> ResponseEntity.ok(reminder))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Create new Service Reminder
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<ServiceReminder> createServiceReminder(@RequestParam("teamId") Integer teamId, 
            @RequestParam("vehicleId") Integer vehicleId,
            @RequestParam("status") String status, 
            @RequestParam("nextDueDate") LocalDate nextDueDate,
            @RequestParam("serviceTaskId") Integer serviceTaskId,
            @RequestParam(value = "activeWorkOrder", required = false) String activeWorkOrder,
            @RequestParam(value = "lastCompletedDate", required = false) LocalDate lastCompletedDate,
            @RequestParam(value = "compliance", required = false) String compliance,
            @RequestParam(value = "watcherId", required = false) Integer watcherId) {
        
        ServiceReminder serviceReminder = new ServiceReminder();
        
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        Team team = teamService.getTeamById(teamId);
        ServiceTask serviceTask = serviceTaskService.getServiceTaskById(serviceTaskId);
        User watcher = watcherId != null ? userService.getUserById(watcherId) : null;
        
        serviceReminder.setTeam(team);
        serviceReminder.setVehicle(vehicle);
        serviceReminder.setStatus(status);
        serviceReminder.setNextDueDate(nextDueDate);
        serviceReminder.setServiceTask(serviceTask);
        serviceReminder.setActiveWorkOrder(activeWorkOrder);
        serviceReminder.setLastCompletedDate(lastCompletedDate);
        serviceReminder.setCompliance(compliance);
        serviceReminder.setWatchers(watcher);
        
        serviceReminderService.createServiceReminder(serviceReminder);

        return ResponseEntity.ok(serviceReminder);
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<ServiceReminder> updateServiceReminder(@PathVariable Integer id, 
                                        @RequestParam Integer teamId,
                                        @RequestParam Integer vehicleId, 
                                        @RequestParam String status, 
                                        @RequestParam String nextDueDate,
                                        @RequestParam Integer serviceTaskId, 
                                        @RequestParam(value = "activeWorkOrder", required = false) String activeWorkOrder,
                                        @RequestParam(value = "lastCompletedDate", required = false) String lastCompletedDate, 
                                        @RequestParam(value = "compliance", required = false) String compliance,
                                        @RequestParam(value = "watcherId", required = false) Integer watcherId) {
        System.out.println(teamId);
    	
    	return serviceReminderService.getServiceReminderById(id)
                .map(serviceReminder -> {
                    Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
                    Team team = teamService.getTeamById(teamId);
                    ServiceTask serviceTask = serviceTaskService.getServiceTaskById(serviceTaskId);
                    User watcher = watcherId != null ? userService.getUserById(watcherId) : null;
                    
                    serviceReminder.setTeam(team);
                    serviceReminder.setVehicle(vehicle);
                    serviceReminder.setStatus(status);
                    serviceReminder.setNextDueDate(LocalDate.parse(nextDueDate));
                    serviceReminder.setServiceTask(serviceTask);
                    serviceReminder.setActiveWorkOrder(activeWorkOrder);
                    serviceReminder.setLastCompletedDate(lastCompletedDate != null ? LocalDate.parse(lastCompletedDate) : null);
                    serviceReminder.setCompliance(compliance);
                    serviceReminder.setWatchers(watcher);
                    
                    serviceReminderService.updateServiceReminder(serviceReminder);
                    return ResponseEntity.ok(serviceReminder);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    // Delete Service Reminder
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteServiceReminder(@PathVariable Integer id) {
        if (serviceReminderService.deleteServiceReminder(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
