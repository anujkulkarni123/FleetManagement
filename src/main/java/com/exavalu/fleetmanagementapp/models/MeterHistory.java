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
public class MeterHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;
    
    @Column(nullable = false)
    private LocalDateTime meterDate;
    
    @Column(nullable = false)
    private int meterValue;
    
    @Column(nullable = false)
    private String meterType;
    
    @Column(nullable = false)
    private String source;
    
    @Column(nullable = false)
    private boolean isVoid; // Changed from "void" to "isVoid" to avoid keyword conflict
    
    @Column(nullable = false)
    private String voidStatus;
    
    @Column(nullable = false)
    private String autoVoidReason; // Changed from "auto-void reason" to "autoVoidReason" to follow Java variable naming conventions

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

	public LocalDateTime getMeterDate() {
		return meterDate;
	}

	public void setMeterDate(LocalDateTime meterDate) {
		this.meterDate = meterDate;
	}

	public int getMeterValue() {
		return meterValue;
	}

	public void setMeterValue(int meterValue) {
		this.meterValue = meterValue;
	}

	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isVoid() {
		return isVoid;
	}

	public void setVoid(boolean isVoid) {
		this.isVoid = isVoid;
	}

	public String getVoidStatus() {
		return voidStatus;
	}

	public void setVoidStatus(String voidStatus) {
		this.voidStatus = voidStatus;
	}

	public String getAutoVoidReason() {
		return autoVoidReason;
	}

	public void setAutoVoidReason(String autoVoidReason) {
		this.autoVoidReason = autoVoidReason;
	}
    
//    @ManyToOne
//    @JoinColumn(name = "teamId", nullable = false)
//    private Team team;

    // Getters and Setters if needed
}