package com.exavalu.fleetmanagementapp.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Service")
public class ServiceReminder extends Reminder {

    @ManyToOne
    @JoinColumn(name = "serviceTaskId", nullable = true)
    private ServiceTask serviceTask;

    @Column(nullable = true)
    private String activeWorkOrder;

    @Column(nullable = true)
    private LocalDate lastCompletedDate;

    @Column(nullable = true)
    private String compliance;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = true)
    private User watchers; // Changed to a single watcher and made nullable

    public String getActiveWorkOrder() {
        return activeWorkOrder;
    }

    public void setActiveWorkOrder(String activeWorkOrder) {
        this.activeWorkOrder = activeWorkOrder;
    }

    public LocalDate getLastCompletedDate() {
        return lastCompletedDate;
    }

    public void setLastCompletedDate(LocalDate lastCompletedDate) {
        this.lastCompletedDate = lastCompletedDate;
    }

    public String getCompliance() {
        return compliance;
    }

    public void setCompliance(String compliance) {
        this.compliance = compliance;
    }

    public User getWatchers() {
        return watchers;
    }

    public void setWatchers(User watchers) {
        this.watchers = watchers;
    }

    public ServiceTask getServiceTask() {
        return serviceTask;
    }

    public void setServiceTask(ServiceTask serviceTask) {
        this.serviceTask = serviceTask;
    }
}
