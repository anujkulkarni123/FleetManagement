package com.exavalu.fleetmanagementapp.models;

import java.time.LocalDateTime;

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
public class ItemFailures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private LocalDateTime date;
    
    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;
    
    @Column(nullable = false)
    private String itemFailures; // Assuming this is a description of the item failures
    
    @ManyToOne
    @JoinColumn(name = "inspectionId", nullable = false)
    private Inspections inspection;
    
    @ManyToOne
    @JoinColumn(name = "issueId", nullable = false)
    private Issues issue; // Assuming there is an Issue class

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getItemFailures() {
		return itemFailures;
	}

	public void setItemFailures(String itemFailures) {
		this.itemFailures = itemFailures;
	}

	public Inspections getInspection() {
		return inspection;
	}

	public void setInspection(Inspections inspection) {
		this.inspection = inspection;
	}

	public Issues getIssue() {
		return issue;
	}

	public void setIssue(Issues issue) {
		this.issue = issue;
	}

    
}