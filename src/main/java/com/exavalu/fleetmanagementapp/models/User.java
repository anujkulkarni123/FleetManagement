package com.exavalu.fleetmanagementapp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String verificationCode;
    
    @Column(name = "userRoleId", columnDefinition = "bigint default 2")
    private final Long userRoleId = 2L;
    
    @ManyToOne
    @JoinColumn(name = "userRoleId", referencedColumnName = "roleId",insertable = false, updatable = false)
    private Role role;	
    private final int status = 1;
    private boolean loggedIn;
    private String imagePath;
    
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private String volumeUnit;

    @Column(nullable = false)
    private String pageItems;

    @ManyToOne
    @JoinColumn(name = "watching_equipment_id")
    private Equipment watchingEquipment;

    @OneToMany(mappedBy = "user")
    private List<Vehicle> vehicles;

    @ManyToOne
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getVolumeUnit() {
		return volumeUnit;
	}

	public void setVolumeUnit(String volumeUnit) {
		this.volumeUnit = volumeUnit;
	}

	public String getPageItems() {
		return pageItems;
	}

	public void setPageItems(String pageItems) {
		this.pageItems = pageItems;
	}

	public Equipment getWatchingEquipment() {
		return watchingEquipment;
	}

	public void setWatchingEquipment(Equipment watchingEquipment) {
		this.watchingEquipment = watchingEquipment;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public int getStatus() {
		return status;
	}
	
	
}
