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
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ServiceTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;
    
    @ElementCollection
    private List<String> serviceEntries;
    
    @ElementCollection
    private List<String> serviceReminders;
    
    @ElementCollection
    private List<String> servicePrograms;
    
    @ElementCollection
    private List<String> workOrders;
    
    @Column(nullable = false)
    private String defaultRepairReason;
    
    @Column(nullable = false)
    private String defaultCategoryCode;
    
    @Column(nullable = false)
    private String defaultSystemCode;
    
    @Column(nullable = false)
    private String defaultAssemblyCode;
    
    @ManyToOne
    @JoinColumn(name = "serviceId", nullable = false)
    private FleetService service;
    
    @ManyToOne
    @JoinColumn(name = "workOrderId", nullable = false)
    private WorkOrder workOrder;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getServiceEntries() {
		return serviceEntries;
	}

	public void setServiceEntries(List<String> serviceEntries) {
		this.serviceEntries = serviceEntries;
	}

	public List<String> getServiceReminders() {
		return serviceReminders;
	}

	public void setServiceReminders(List<String> serviceReminders) {
		this.serviceReminders = serviceReminders;
	}

	public List<String> getServicePrograms() {
		return servicePrograms;
	}

	public void setServicePrograms(List<String> servicePrograms) {
		this.servicePrograms = servicePrograms;
	}

	public List<String> getWorkOrders() {
		return workOrders;
	}

	public void setWorkOrders(List<String> workOrders) {
		this.workOrders = workOrders;
	}

	public String getDefaultRepairReason() {
		return defaultRepairReason;
	}

	public void setDefaultRepairReason(String defaultRepairReason) {
		this.defaultRepairReason = defaultRepairReason;
	}

	public String getDefaultCategoryCode() {
		return defaultCategoryCode;
	}

	public void setDefaultCategoryCode(String defaultCategoryCode) {
		this.defaultCategoryCode = defaultCategoryCode;
	}

	public String getDefaultSystemCode() {
		return defaultSystemCode;
	}

	public void setDefaultSystemCode(String defaultSystemCode) {
		this.defaultSystemCode = defaultSystemCode;
	}

	public String getDefaultAssemblyCode() {
		return defaultAssemblyCode;
	}

	public void setDefaultAssemblyCode(String defaultAssemblyCode) {
		this.defaultAssemblyCode = defaultAssemblyCode;
	}

	public FleetService getService() {
		return service;
	}

	public void setService(FleetService service) {
		this.service = service;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

    
}
