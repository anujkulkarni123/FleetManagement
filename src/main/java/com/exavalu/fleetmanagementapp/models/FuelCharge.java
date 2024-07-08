package com.exavalu.fleetmanagementapp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class FuelCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String vendor;

    @Column(nullable = false)
    private String meter;

    @Column(nullable = false)
    private double usageField;

    @Column(nullable = false)
    private double volume;

    @Column(nullable = false)
    private double totalCost;

    @Column
    private String alerts;

    @Column
    private double capacityExceptionVolume;

    @Column
    private double locationExceptionDistance;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getMeter() {
		return meter;
	}

	public void setMeter(String meter) {
		this.meter = meter;
	}

	public double getUsageField() {
		return usageField;
	}

	public void setUsageField(double usage) {
		this.usageField = usage;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getAlerts() {
		return alerts;
	}

	public void setAlerts(String alerts) {
		this.alerts = alerts;
	}

	public double getCapacityExceptionVolume() {
		return capacityExceptionVolume;
	}

	public void setCapacityExceptionVolume(double capacityExceptionVolume) {
		this.capacityExceptionVolume = capacityExceptionVolume;
	}

	public double getLocationExceptionDistance() {
		return locationExceptionDistance;
	}

	public void setLocationExceptionDistance(double locationExceptionDistance) {
		this.locationExceptionDistance = locationExceptionDistance;
	}
    
    
}
