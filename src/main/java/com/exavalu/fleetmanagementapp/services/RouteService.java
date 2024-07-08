package com.exavalu.fleetmanagementapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exavalu.fleetmanagementapp.models.Route;
import com.exavalu.fleetmanagementapp.repositories.RouteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public Optional<Route> findById(int id) {
        return routeRepository.findById(id);
    }

    public void save(Route route) {
        routeRepository.save(route);
    }

    public void delete(int id) {
        routeRepository.deleteById(id);
    }
}
