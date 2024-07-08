package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.ItemFailures;
import com.exavalu.fleetmanagementapp.repositories.ItemFailuresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemFailuresService {

    @Autowired
    private ItemFailuresRepository itemFailuresRepository;

    public List<ItemFailures> getAllItemFailures() {
        return itemFailuresRepository.findAll();
    }

    public Optional<ItemFailures> getItemFailuresById(Integer id) {
        return itemFailuresRepository.findById(id);
    }

    public ItemFailures createItemFailures(ItemFailures itemFailures) {
        return itemFailuresRepository.save(itemFailures);
    }

    public Optional<ItemFailures> updateItemFailures(Integer id, ItemFailures itemFailuresDetails) {
        return itemFailuresRepository.findById(id).map(itemFailures -> {
            itemFailures.setDate(itemFailuresDetails.getDate());
            itemFailures.setVehicle(itemFailuresDetails.getVehicle());
            itemFailures.setItemFailures(itemFailuresDetails.getItemFailures());
            itemFailures.setInspection(itemFailuresDetails.getInspection());
            itemFailures.setIssue(itemFailuresDetails.getIssue());
            return itemFailuresRepository.save(itemFailures);
        });
    }

    public boolean deleteItemFailures(Integer id) {
        return itemFailuresRepository.findById(id).map(itemFailures -> {
            itemFailuresRepository.delete(itemFailures);
            return true;
        }).orElse(false);
    }
}
