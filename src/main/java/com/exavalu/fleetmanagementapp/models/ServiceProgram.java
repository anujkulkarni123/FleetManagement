package com.exavalu.fleetmanagementapp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ServiceProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;
    
    @Column(nullable = false)
    private String serviceProgram;
    
    @ElementCollection
    private List<String> vehicles;
    
    @ElementCollection
    private List<String> schedules;
    
    @Column(nullable = false)
    private String primaryMeter;
    
    @Column(nullable = false)
    private String secondaryMeter;

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

	public String getServiceProgram() {
		return serviceProgram;
	}

	public void setServiceProgram(String serviceProgram) {
		this.serviceProgram = serviceProgram;
	}

	public List<String> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<String> vehicles) {
		this.vehicles = vehicles;
	}

	public List<String> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<String> schedules) {
		this.schedules = schedules;
	}

	public String getPrimaryMeter() {
		return primaryMeter;
	}

	public void setPrimaryMeter(String primaryMeter) {
		this.primaryMeter = primaryMeter;
	}

	public String getSecondaryMeter() {
		return secondaryMeter;
	}

	public void setSecondaryMeter(String secondaryMeter) {
		this.secondaryMeter = secondaryMeter;
	}

    
}
