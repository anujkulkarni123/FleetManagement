package com.exavalu.fleetmanagementapp.dto;

import java.util.List;

public class SelectedItemsDTO {
    private List<String> vehicles;
    private List<Integer> serviceTasks;

    // Getters and Setters
    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Integer> getServiceTasks() {
        return serviceTasks;
    }

    public void setServiceTasks(List<Integer> serviceTasks) {
        this.serviceTasks = serviceTasks;
    }
}
