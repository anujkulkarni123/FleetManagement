package com.exavalu.fleetmanagementapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exavalu.fleetmanagementapp.models.Country;
import com.exavalu.fleetmanagementapp.repositories.CountryRepository;
import com.exavalu.fleetmanagementapp.repositories.StateRepository;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    // Get All Countries
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    // Get Country By Id
    public Optional<Country> findById(int id) {
        return countryRepository.findById(id);
    }

    // Delete Country and associated States
    public void delete(int id) {
        Optional<Country> country = countryRepository.findById(id);
        country.ifPresent(value -> {
            stateRepository.deleteAll(value.getStates());
            countryRepository.deleteById(id);
        });
    }

    // Save or Update Country
    public void save(Country country) {
        countryRepository.save(country);
    }
}
