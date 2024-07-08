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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BillingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billingInfoid;
    
    @ManyToOne
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;
    
    @Column(nullable = false)
    private String billingPlan;
    
    @Column(nullable = false)
    private String addOns; // Changed from "add-ons" to "addOns" to follow Java variable naming conventions
    
    @Column(nullable = false)
    private String billingProfileId;

	public Integer getId() {
		return billingInfoid;
	}

	public void setId(Integer id) {
		this.billingInfoid = id;
	}

	public Team getUser() {
		return team;
	}

	public void setUser(Team team) {
		this.team = team;
	}

	public String getBillingPlan() {
		return billingPlan;
	}

	public void setBillingPlan(String billingPlan) {
		this.billingPlan = billingPlan;
	}

	public String getAddOns() {
		return addOns;
	}

	public void setAddOns(String addOns) {
		this.addOns = addOns;
	}

	public String getBillingProfileId() {
		return billingProfileId;
	}

	public void setBillingProfileId(String billingProfileId) {
		this.billingProfileId = billingProfileId;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

    // Getters and Setters if needed
}
