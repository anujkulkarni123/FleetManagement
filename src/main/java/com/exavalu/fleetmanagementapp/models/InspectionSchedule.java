package com.exavalu.fleetmanagementapp.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class InspectionSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;
    
    @Column(nullable = false)
    private String inspectionForm;
    
    @ManyToOne
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;
    
    @Column(nullable = false)
    private LocalDate nextDue;
    
    @Column(nullable = false)
    private LocalDate lastInspected;
    
    @Column(nullable = false)
    private String frequency;
    
    @Column(nullable = false)
    private boolean manualOverridden; // Corrected typo from "manuallOveridden"

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getInspectionForm() {
		return inspectionForm;
	}

	public void setInspectionForm(String inspectionForm) {
		this.inspectionForm = inspectionForm;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public LocalDate getNextDue() {
		return nextDue;
	}

	public void setNextDue(LocalDate nextDue) {
		this.nextDue = nextDue;
	}

	public LocalDate getLastInspected() {
		return lastInspected;
	}

	public void setLastInspected(LocalDate lastInspected) {
		this.lastInspected = lastInspected;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public boolean isManualOverridden() {
		return manualOverridden;
	}

	public void setManualOverridden(boolean manualOverridden) {
		this.manualOverridden = manualOverridden;
	}

    
}