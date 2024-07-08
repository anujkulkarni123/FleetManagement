package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.WorkOrder;
import com.exavalu.fleetmanagementapp.repositories.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;

    public List<WorkOrder> getAllWorkOrders() {
        return workOrderRepository.findAll();
    }

    public Optional<WorkOrder> getWorkOrderById(Integer id) {
        return workOrderRepository.findById(id);
    }

    public WorkOrder createWorkOrder(WorkOrder workOrder) {
        return workOrderRepository.save(workOrder);
    }

    public Optional<WorkOrder> updateWorkOrder(Integer id, WorkOrder workOrderDetails) {
        return workOrderRepository.findById(id).map(workOrder -> {
            workOrder.setTeam(workOrderDetails.getTeam());
            workOrder.setVehicle(workOrderDetails.getVehicle());
            workOrder.setStatus(workOrderDetails.getStatus());
            workOrder.setPriorityClass(workOrderDetails.getPriorityClass());
            workOrder.setServiceTasks(workOrderDetails.getServiceTasks());
            workOrder.setIssueDate(workOrderDetails.getIssueDate());
            workOrder.setExpectedCompletionDate(workOrderDetails.getExpectedCompletionDate());
            workOrder.setAssignedTo(workOrderDetails.getAssignedTo());
            workOrder.setWatcher(workOrderDetails.getWatcher());
            workOrder.setOperator(workOrderDetails.getOperator());
            return workOrderRepository.save(workOrder);
        });
    }

    public boolean deleteWorkOrder(Integer id) {
        return workOrderRepository.findById(id).map(workOrder -> {
            workOrderRepository.delete(workOrder);
            return true;
        }).orElse(false);
    }
}
