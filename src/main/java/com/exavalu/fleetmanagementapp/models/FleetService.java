package com.exavalu.fleetmanagementapp.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class FleetService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;
    
    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;
    
    @Column(nullable = false)
    private LocalDateTime completionDate;
    
    @Column(nullable = false)
    private String watchers;
    
    @Column(nullable = false)
    private String priorityClass;
    
    @Column(nullable = false)
    private String meter;
    
    @ElementCollection
    private List<String> serviceTasks;
    
    @OneToMany(mappedBy = "service")
    private List<Issues> issues;
    
    @Column(nullable = false)
    private String vendor;
    
    @Column(nullable = false)
    private double totalPrice;
    
    @Column(nullable = false)
    private String workOrder;
    
    @Column(nullable = false)
    private String labels;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public LocalDateTime getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDateTime completionDate) {
		this.completionDate = completionDate;
	}

	public String getWatchers() {
		return watchers;
	}

	public void setWatchers(String watchers) {
		this.watchers = watchers;
	}

	public String getPriorityClass() {
		return priorityClass;
	}

	public void setPriorityClass(String priorityClass) {
		this.priorityClass = priorityClass;
	}

	public String getMeter() {
		return meter;
	}

	public void setMeter(String meter) {
		this.meter = meter;
	}

	public List<String> getServiceTasks() {
		return serviceTasks;
	}

	public void setServiceTasks(List<String> serviceTasks) {
		this.serviceTasks = serviceTasks;
	}

	public List<Issues> getIssues() {
		return issues;
	}

	public void setIssues(List<Issues> issues) {
		this.issues = issues;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

    
}