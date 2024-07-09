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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Forms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer formId;
    
    @ManyToOne
    @JoinColumn(name = "inspectionId", nullable = false)
    private Inspections inspection;
    
    @Column(nullable = false)
    private String formType;

	public Integer getId() {
		return formId;
	}

	public void setId(Integer id) {
		this.formId = id;
	}

	public Inspections getInspection() {
		return inspection;
	}

	public void setInspection(Inspections inspection) {
		this.inspection = inspection;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

    
}
