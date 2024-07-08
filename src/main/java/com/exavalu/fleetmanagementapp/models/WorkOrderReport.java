
package com.exavalu.fleetmanagementapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("WorkOrder")
public class WorkOrderReport extends Report {

	@Column(nullable = false)
	private String reportForm;

	public Object getReportForm() {
		return reportForm;
	}

	public void setReportForm(String reportForm2) {
		this.reportForm = reportForm2;

	}

}
