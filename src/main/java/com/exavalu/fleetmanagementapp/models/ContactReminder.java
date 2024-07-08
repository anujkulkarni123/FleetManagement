package com.exavalu.fleetmanagementapp.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Contact")
public class ContactReminder extends Reminder {

    private String renewalType;
    private LocalDate dueDate;
    private String watchers;
    
	public String getRenewalType() {
		return renewalType;
	}
	public void setRenewalType(String renewalType) {
		this.renewalType = renewalType;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public String getWatchers() {
		return watchers;
	}
	public void setWatchers(String watchers) {
		this.watchers = watchers;
	}

    
}
