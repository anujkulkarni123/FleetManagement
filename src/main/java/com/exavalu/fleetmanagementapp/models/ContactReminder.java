package com.exavalu.fleetmanagementapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(nullable = true)
    private String renewalType;

    @Column(nullable = true)
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = true)
    private User watchers;
    
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

	public User getWatchers() {
		return watchers;
	}

	public void setWatchers(User watchers) {
		this.watchers = watchers;
	}
    
    
}
