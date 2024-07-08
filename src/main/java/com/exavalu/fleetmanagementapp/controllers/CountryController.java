package com.exavalu.fleetmanagementapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exavalu.fleetmanagementapp.models.Country;
import com.exavalu.fleetmanagementapp.services.CountryService;

import java.util.Optional;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/countries")
    public String findAll(Model model) {
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("country", new Country());
        return "country";
    }

    @GetMapping("/countries/findById/{id}")
    public String editCountry(@PathVariable("id") int id, Model model) {
        Optional<Country> country = countryService.findById(id);
        if (country.isPresent()) {
            model.addAttribute("country", country.get());
        } else {
            model.addAttribute("country", new Country());
        }
        model.addAttribute("countries", countryService.findAll());
        return "country";
    }

    @PostMapping("/countries/save")
    public String addCountry(@RequestParam String countryCode, @RequestParam String capital,
                             @RequestParam String description, @RequestParam String nationality,
                             @RequestParam String continent, @RequestParam(required = false) Integer countryId) {

        Country country = new Country();
        if (countryId != null) {
            Optional<Country> optionalCountry = countryService.findById(countryId);
            if (optionalCountry.isPresent()) {
                country = optionalCountry.get();
            }
        }
        
        country.setCountryCode(countryCode);
        country.setCapital(capital);
        country.setDescription(description);
        country.setNationality(nationality);
        country.setContinent(continent);

        countryService.save(country);

        return "redirect:/countries";
    }

    @GetMapping("/countries/delete/{id}")
    public String deleteCountry(@PathVariable("id") int id, Model model) {
        try {
            countryService.delete(id);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Cannot delete country with associated states.");
            return "redirect:/countries";
        }
        return "redirect:/countries";
    }
}
