package com.exavalu.fleetmanagementapp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VehicleAssignments {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private long duration; // Assuming duration is stored in seconds or another unit

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User operator;

    @Column(name = "usage_info") // Renamed column to avoid conflict with reserved keyword
    private String usage;

    @Column(nullable = false)
    private int startingOdometer;

    @Column(nullable = false)
    private int endingOdometer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle2) {
		this.vehicle = vehicle2;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator2) {
		this.operator = operator2;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public int getStartingOdometer() {
		return startingOdometer;
	}

	public void setStartingOdometer(int startingOdometer) {
		this.startingOdometer = startingOdometer;
	}

	public int getEndingOdometer() {
		return endingOdometer;
	}

	public void setEndingOdometer(int endingOdometer) {
		this.endingOdometer = endingOdometer;
	}
}
