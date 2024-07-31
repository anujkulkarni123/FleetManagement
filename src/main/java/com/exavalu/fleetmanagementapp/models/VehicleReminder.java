package com.exavalu.fleetmanagementapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Vehicle")
public class VehicleReminder extends Reminder {

    @Column(nullable = true)
    private String renewalType;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User watchers;

	public String getRenewalType() {
		return renewalType;
	}

	public void setRenewalType(String renewalType) {
		this.renewalType = renewalType;
	}

	public User getWatchers() {
		return watchers;
	}

	public void setWatchers(User watchers) {
		this.watchers = watchers;
	}

}
