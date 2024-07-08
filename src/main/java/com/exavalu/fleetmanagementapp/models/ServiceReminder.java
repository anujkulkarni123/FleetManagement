package com.exavalu.fleetmanagementapp.models;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Service")
public class ServiceReminder extends Reminder {

    private String activeWorkOrder;
    private LocalDate lastCompletedDate;
    private String compliance;
    private String watchers;
	
    
    public String getActiveWorkOrder() {
		return activeWorkOrder;
	}
	public void setActiveWorkOrder(String activeWorkOrder) {
		this.activeWorkOrder = activeWorkOrder;
	}
	public LocalDate getLastCompletedDate() {
		return lastCompletedDate;
	}
	public void setLastCompletedDate(LocalDate lastCompletedDate) {
		this.lastCompletedDate = lastCompletedDate;
	}
	public String getCompliance() {
		return compliance;
	}
	public void setCompliance(String compliance) {
		this.compliance = compliance;
	}
	public String getWatchers() {
		return watchers;
	}
	public void setWatchers(String watchers) {
		this.watchers = watchers;
	}

    
}