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
public class NotificationSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private boolean vehicleNotif;
    
    @Column(nullable = false)
    private boolean inspectionNotif;
    
    @Column(nullable = false)
    private boolean issueNotif;
    
    @Column(nullable = false)
    private boolean workOrderNotif;
    
    @Column(nullable = false)
    private boolean serviceEntryNotif;
    
    @Column(nullable = false)
    private boolean serviceReminderNotif;
    
    @Column(nullable = false)
    private boolean vehicleReminderNotif;
    
    @Column(nullable = false)
    private boolean contactReminderNotif;
    
    @Column(nullable = false)
    private boolean fuelEntryNotif;
    
    @Column(nullable = false)
    private boolean chargingEntryNotif;
    
    @Column(nullable = false)
    private boolean partsNotif;
    
    @Column(nullable = false)
    private boolean vendorNotifs;
    
    @Column(nullable = false)
    private boolean expenseNotif;
    
    @Column(nullable = false)
    private boolean integrationNotif;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isVehicleNotif() {
		return vehicleNotif;
	}

	public void setVehicleNotif(boolean vehicleNotif) {
		this.vehicleNotif = vehicleNotif;
	}

	public boolean isInspectionNotif() {
		return inspectionNotif;
	}

	public void setInspectionNotif(boolean inspectionNotif) {
		this.inspectionNotif = inspectionNotif;
	}

	public boolean isIssueNotif() {
		return issueNotif;
	}

	public void setIssueNotif(boolean issueNotif) {
		this.issueNotif = issueNotif;
	}

	public boolean isWorkOrderNotif() {
		return workOrderNotif;
	}

	public void setWorkOrderNotif(boolean workOrderNotif) {
		this.workOrderNotif = workOrderNotif;
	}

	public boolean isServiceEntryNotif() {
		return serviceEntryNotif;
	}

	public void setServiceEntryNotif(boolean serviceEntryNotif) {
		this.serviceEntryNotif = serviceEntryNotif;
	}

	public boolean isServiceReminderNotif() {
		return serviceReminderNotif;
	}

	public void setServiceReminderNotif(boolean serviceReminderNotif) {
		this.serviceReminderNotif = serviceReminderNotif;
	}

	public boolean isVehicleReminderNotif() {
		return vehicleReminderNotif;
	}

	public void setVehicleReminderNotif(boolean vehicleReminderNotif) {
		this.vehicleReminderNotif = vehicleReminderNotif;
	}

	public boolean isContactReminderNotif() {
		return contactReminderNotif;
	}

	public void setContactReminderNotif(boolean contactReminderNotif) {
		this.contactReminderNotif = contactReminderNotif;
	}

	public boolean isFuelEntryNotif() {
		return fuelEntryNotif;
	}

	public void setFuelEntryNotif(boolean fuelEntryNotif) {
		this.fuelEntryNotif = fuelEntryNotif;
	}

	public boolean isChargingEntryNotif() {
		return chargingEntryNotif;
	}

	public void setChargingEntryNotif(boolean chargingEntryNotif) {
		this.chargingEntryNotif = chargingEntryNotif;
	}

	public boolean isPartsNotif() {
		return partsNotif;
	}

	public void setPartsNotif(boolean partsNotif) {
		this.partsNotif = partsNotif;
	}

	public boolean isVendorNotifs() {
		return vendorNotifs;
	}

	public void setVendorNotifs(boolean vendorNotifs) {
		this.vendorNotifs = vendorNotifs;
	}

	public boolean isExpenseNotif() {
		return expenseNotif;
	}

	public void setExpenseNotif(boolean expenseNotif) {
		this.expenseNotif = expenseNotif;
	}

	public boolean isIntegrationNotif() {
		return integrationNotif;
	}

	public void setIntegrationNotif(boolean integrationNotif) {
		this.integrationNotif = integrationNotif;
	}

    // Getters and Setters if needed
}
