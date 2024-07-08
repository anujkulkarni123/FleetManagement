package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.MeterHistory;
import com.exavalu.fleetmanagementapp.services.MeterHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IssuesViewController {

    @GetMapping("/issues/1")
    public String viewIssue(Model model) {
        return "issue";
    }
    
    @GetMapping("/issue")
    public String viewGeneralIssueModel(Model model) {
        return "issue";
    }
}