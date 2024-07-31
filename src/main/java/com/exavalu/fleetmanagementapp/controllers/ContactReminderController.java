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

import com.exavalu.fleetmanagementapp.models.ContactReminder;
import com.exavalu.fleetmanagementapp.models.Team;
import com.exavalu.fleetmanagementapp.models.User;
import com.exavalu.fleetmanagementapp.services.ContactReminderService;
import com.exavalu.fleetmanagementapp.services.TeamService;
import com.exavalu.fleetmanagementapp.services.UserService;

@Controller
@RequestMapping("/contact-reminders")
public class ContactReminderController {

    @Autowired
    private ContactReminderService contactReminderService;

    @Autowired
    private TeamService teamService;

    @Autowired 
    private UserService userService;

    @GetMapping
    public String getAllContactReminders(Model model) {
        List<ContactReminder> reminders = contactReminderService.getAllContactReminders();
        Team team = teamService.getTeamById(1);
        List<User> users = userService.getAllUsers();
        model.addAttribute("team", team);
        model.addAttribute("reminders", reminders);
        model.addAttribute("watcherList", users);
        
        return "contactReminder"; // This should match the name of the HTML file (contactReminder.html)
    }

    // Get ContactReminder by ID
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ContactReminder> getContactReminderById(@PathVariable Integer id) {
        return contactReminderService.getContactReminderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new ContactReminder
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<ContactReminder> createContactReminder(@RequestParam("teamId") Integer teamId,
            @RequestParam("status") String status, 
            @RequestParam("nextDueDate") LocalDate nextDueDate,
            @RequestParam(value = "renewalType", required = false) String renewalType,
            @RequestParam(value = "dueDate", required = false) LocalDate dueDate,
            @RequestParam(value = "watcherId", required = false) Integer watcherId) {
        
        ContactReminder contactReminder = new ContactReminder();
        
        Team team = teamService.getTeamById(teamId);
        User watcher = watcherId != null ? userService.getUserById(watcherId) : null;
        
        contactReminder.setTeam(team);
        contactReminder.setStatus(status);
        contactReminder.setNextDueDate(nextDueDate);
        contactReminder.setRenewalType(renewalType);
        contactReminder.setDueDate(dueDate);
        contactReminder.setWatchers(watcher != null ? watcher : null);
        
        contactReminderService.createContactReminder(contactReminder);

        return ResponseEntity.ok(contactReminder);
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<ContactReminder> updateContactReminder(@PathVariable Integer id, 
                                        @RequestParam Integer teamId, 
                                        @RequestParam String status, 
                                        @RequestParam String nextDueDate,
                                        @RequestParam(value = "renewalType", required = false) String renewalType,
                                        @RequestParam(value = "dueDate", required = false) String dueDate,
                                        @RequestParam(value = "watcherId", required = false) Integer watcherId) {
    	
    	System.out.println(id);
    	
    	System.out.println(status);
        return contactReminderService.getContactReminderById(id)
                .map(contactReminder -> {
                    Team team = teamService.getTeamById(teamId);
                    User watcher = watcherId != null ? userService.getUserById(watcherId) : null;
                    
                    contactReminder.setTeam(team);
                    contactReminder.setStatus(status);
                    contactReminder.setNextDueDate(LocalDate.parse(nextDueDate));
                    contactReminder.setRenewalType(renewalType);
                    contactReminder.setDueDate(dueDate != null ? LocalDate.parse(dueDate) : null);
                    contactReminder.setWatchers(watcher != null ? watcher : null);
                    
                    contactReminderService.updateContactReminder(contactReminder);
                    return ResponseEntity.ok(contactReminder);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete Contact Reminder
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteContactReminder(@PathVariable Integer id) {
        if (contactReminderService.deleteContactReminder(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Filter Contact Reminders
    @PostMapping("/filter")
    @ResponseBody
    public List<ContactReminder> getFilteredContactReminders(@RequestBody Map<String, List<Integer>> filterCriteria) {
        List<Integer> teamIds = filterCriteria.get("teams");

        List<ContactReminder> allReminders = contactReminderService.getAllContactReminders();

        // Manual filtering with handling for empty filter criteria
        List<ContactReminder> filteredReminders = allReminders.stream()
                .filter(reminder -> (teamIds.isEmpty() || teamIds.contains(reminder.getTeam().getTeamId())))
                .collect(Collectors.toList());

        // Extracting and printing the filtered reminder IDs
        List<Integer> filteredReminderIds = filteredReminders.stream()
                .map(ContactReminder::getId)
                .collect(Collectors.toList());

        System.out.println("Filtered Contact Reminder IDs: " + filteredReminderIds);

        return filteredReminders;
    }
}
