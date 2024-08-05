package com.exavalu.fleetmanagementapp.models;

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

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Issues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	@Column(nullable = false)
    private String priority;
    
//    @ManyToOne
//    @JoinColumn(name = "vehicleId", nullable = false)
//    private Vehicle vehicle;
    
    private int vehicleId;
    
    @Column(nullable = false)
    private String assetType;
    
    @Column(nullable = false)
    private String issueNumber;
    
    @Column(nullable = false)
    private String summary;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private String reportedBy;
    
    @Column(nullable = false)
    private String status;
    
    @Column(nullable = false)
    private String source;
    
    @Column(nullable = false)
    private LocalDateTime reportedDate;
    
//    @ManyToOne
//    @JoinColumn(name = "userId", nullable = false)
//    private User assignedUser;
    
    private int UserId;
    
    @Column
    private String labels;
    
//    @OneToMany
//    @JoinColumn(name = "userId")
//    private List<User> watchers;
//    
//    @ManyToOne
//    @JoinColumn(name = "teamId", nullable = false)
//    private Team team;
//    
//    @ManyToOne
//    @JoinColumn(name = "serviceId", nullable = false)
//    private FleetService service;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}


	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public LocalDateTime getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(LocalDateTime reportedDate) {
		this.reportedDate = reportedDate;
	}


	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}
    
}
