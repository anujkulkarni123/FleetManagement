package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.Recall;
import com.exavalu.fleetmanagementapp.repositories.RecallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecallService {

    @Autowired
    private RecallRepository recallRepository;

    public List<Recall> getAllRecalls() {
        return recallRepository.findAll();
    }

    public Optional<Recall> getRecallById(Integer id) {
        return recallRepository.findById(id);
    }

    public Recall createRecall(Recall recall) {
        return recallRepository.save(recall);
    }

    public Optional<Recall> updateRecall(Integer id, Recall recallDetails) {
        return recallRepository.findById(id).map(recall -> {
            recall.setTeam(recallDetails.getTeam());
            recall.setVehicle(recallDetails.getVehicle());
            recall.setIssuedDate(recallDetails.getIssuedDate());
            recall.setSummary(recallDetails.getSummary());
            recall.setStatus(recallDetails.getStatus());
            recall.setIssue(recallDetails.getIssue());
            recall.setManufacturerCampaignNumber(recallDetails.getManufacturerCampaignNumber());
            recall.setNHTSACampaignNumber(recallDetails.getNHTSACampaignNumber());
            return recallRepository.save(recall);
        });
    }

    public boolean deleteRecall(Integer id) {
        return recallRepository.findById(id).map(recall -> {
            recallRepository.delete(recall);
            return true;
        }).orElse(false);
    }
}
