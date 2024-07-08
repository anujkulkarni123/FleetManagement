package com.exavalu.fleetmanagementapp.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Vehicle")
public class VehicleReminder extends Reminder {

    private String renewalType;
    private String watchers;
    
	public String getRenewalType() {
		return renewalType;
	}
	public void setRenewalType(String renewalType) {
		this.renewalType = renewalType;
	}
	public String getWatchers() {
		return watchers;
	}
	public void setWatchers(String watchers) {
		this.watchers = watchers;
	}

    
}