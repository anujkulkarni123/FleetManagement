package com.exavalu.fleetmanagementapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exavalu.fleetmanagementapp.models.State;
import com.exavalu.fleetmanagementapp.services.StateService;
import com.exavalu.fleetmanagementapp.services.CountryService;
import com.exavalu.fleetmanagementapp.models.Country;

import java.util.List;
import java.util.Optional;

@Controller
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private CountryService countryService;

    @GetMapping("/states")
    public String getStates(Model model) {
        List<State> states = stateService.findAll();
        List<Country> countries = countryService.findAll();
        model.addAttribute("states", states);
        model.addAttribute("countries", countries);
        model.addAttribute("state", new State());
        return "state";
    }

    @GetMapping("/states/findById/{id}")
    public String editState(@PathVariable("id") int id, Model model) {
        Optional<State> state = stateService.findById(id);
        if (state.isPresent()) {
            model.addAttribute("state", state.get());
        } else {
            model.addAttribute("state", new State());
        }
        model.addAttribute("states", stateService.findAll());
        model.addAttribute("countries", countryService.findAll());
        return "state";
    }

    @PostMapping("/states/save")
    public String addState(@RequestParam String stateName, @RequestParam String capital,
                           @RequestParam String code, @RequestParam Integer countryId, 
                           @RequestParam String details, @RequestParam(required = false) Integer stateId) {

        State state = new State();
        if (stateId != null) {
            state.setStateId(stateId);
        }
        state.setStateName(stateName);
        state.setCapital(capital);
        state.setCode(code);
        state.setDetails(details);

        Optional<Country> optionalCountry = countryService.findById(countryId);
        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();
            state.setCountry(country);
            state.setCountryId(countryId);

            stateService.save(state);           
        }

        return "redirect:/states";
    }

    @GetMapping("/states/delete/{id}")
    public String deleteState(@PathVariable("id") int id, Model model) {
        try {
            stateService.delete(id);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Cannot delete state.");
            return "redirect:/states";
        }
        return "redirect:/states";
    }
}
