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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "teamId")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User accountOwner;

    @OneToMany(mappedBy = "team")
    private List<Vehicle> vehicles;

    @Column(nullable = false)
    private String accountName;

    @Column
    private String logo;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String industry;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String dateFormat;

    @Column(nullable = false)
    private String timeZone;

    @Column(nullable = false)
    private String usageUnit;

    @Column(nullable = false)
    private String volumeUnit;

    @Column(nullable = false)
    private String measurementSystem;

    @Column(nullable = false)
    private String tax1;

    @Column(nullable = false)
    private String tax2;

    @Column(nullable = false)
    private boolean taxfree;

    @Column(nullable = false)
    private String secondaryTax;

    @Column(nullable = false)
    private String defaultTax;

    @Column(nullable = false)
    private String companySize;

    @Column(nullable = false)
    private String fleetAssets;

    @Column(nullable = false)
    private String industries;

    @Column(nullable = false)
    private String adminRole;

    @Column(nullable = false)
    private String adminExperience;

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public User getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(User accountOwner) {
		this.accountOwner = accountOwner;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getUsageUnit() {
		return usageUnit;
	}

	public void setUsageUnit(String usageUnit) {
		this.usageUnit = usageUnit;
	}

	public String getVolumeUnit() {
		return volumeUnit;
	}

	public void setVolumeUnit(String volumeUnit) {
		this.volumeUnit = volumeUnit;
	}

	public String getMeasurementSystem() {
		return measurementSystem;
	}

	public void setMeasurementSystem(String measurementSystem) {
		this.measurementSystem = measurementSystem;
	}

	public String getTax1() {
		return tax1;
	}

	public void setTax1(String tax1) {
		this.tax1 = tax1;
	}

	public String getTax2() {
		return tax2;
	}

	public void setTax2(String tax2) {
		this.tax2 = tax2;
	}

	public boolean isTaxfree() {
		return taxfree;
	}

	public void setTaxfree(boolean taxfree) {
		this.taxfree = taxfree;
	}

	public String getSecondaryTax() {
		return secondaryTax;
	}

	public void setSecondaryTax(String secondaryTax) {
		this.secondaryTax = secondaryTax;
	}

	public String getDefaultTax() {
		return defaultTax;
	}

	public void setDefaultTax(String defaultTax) {
		this.defaultTax = defaultTax;
	}

	public String getCompanySize() {
		return companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	public String getFleetAssets() {
		return fleetAssets;
	}

	public void setFleetAssets(String fleetAssets) {
		this.fleetAssets = fleetAssets;
	}

	public String getIndustries() {
		return industries;
	}

	public void setIndustries(String industries) {
		this.industries = industries;
	}

	public String getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}

	public String getAdminExperience() {
		return adminExperience;
	}

	public void setAdminExperience(String adminExperience) {
		this.adminExperience = adminExperience;
	}
    
    
}