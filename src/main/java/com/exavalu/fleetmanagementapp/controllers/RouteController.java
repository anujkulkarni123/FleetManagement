package com.exavalu.fleetmanagementapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exavalu.fleetmanagementapp.models.Route;
import com.exavalu.fleetmanagementapp.services.RouteService;

import java.util.Optional;

@Controller
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/routes")
    public String findAll(Model model) {
        model.addAttribute("routes", routeService.findAll());
        model.addAttribute("route", new Route());
        return "routes";
    }

    @GetMapping("/routes/findById/{id}")
    public String editRoute(@PathVariable("id") int id, Model model) {
        Optional<Route> route = routeService.findById(id);
        if (route.isPresent()) {
            model.addAttribute("route", route.get());
        } else {
            model.addAttribute("route", new Route());
        }
        model.addAttribute("routes", routeService.findAll());
        return "routes";
    }

    @PostMapping("/routes/save")
    public String addRoute(@RequestParam String startLocation, @RequestParam String endLocation,
                           @RequestParam String routeName, @RequestParam(required = false) Integer routeId) {

        Route route = new Route();
        if (routeId != null) {
            Optional<Route> optionalRoute = routeService.findById(routeId);
            if (optionalRoute.isPresent()) {
                route = optionalRoute.get();
            }
        }
        
        route.setRouteName(routeName);
        route.setStartLocation(startLocation);
        route.setEndLocation(endLocation);

        routeService.save(route);

        return "redirect:/routes";
    }

    @GetMapping("/routes/delete/{id}")
    public String deleteRoute(@PathVariable("id") int id, Model model) {
        try {
            routeService.delete(id);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Cannot delete route with associated data.");
            return "redirect:/routes";
        }
        return "redirect:/routes";
    }
}
