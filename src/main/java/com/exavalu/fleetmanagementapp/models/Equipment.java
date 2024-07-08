package com.exavalu.fleetmanagementapp.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
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
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false, unique = true)
    private String serialNumber;

    @Column(nullable = false)
    private String group;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User currentAssignee;

    @Column
    private String labels;

    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle linkedVehicle;

    @ManyToOne
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;

    @OneToMany(mappedBy = "watchingEquipment")
    private List<User> watchers;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getCurrentAssignee() {
		return currentAssignee;
	}

	public void setCurrentAssignee(User currentAssignee) {
		this.currentAssignee = currentAssignee;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public Vehicle getLinkedVehicle() {
		return linkedVehicle;
	}

	public void setLinkedVehicle(Vehicle linkedVehicle) {
		this.linkedVehicle = linkedVehicle;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<User> getWatchers() {
		return watchers;
	}

	public void setWatchers(List<User> watchers) {
		this.watchers = watchers;
	}
    
    
}