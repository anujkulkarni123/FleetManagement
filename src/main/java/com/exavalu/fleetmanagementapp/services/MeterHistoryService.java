package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.MeterHistory;
import com.exavalu.fleetmanagementapp.repositories.MeterHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeterHistoryService {

    @Autowired
    private MeterHistoryRepository meterHistoryRepository;

    public List<MeterHistory> getAllMeterHistories() {
        return meterHistoryRepository.findAll();
    }

    public Optional<MeterHistory> getMeterHistoryById(Integer id) {
        return meterHistoryRepository.findById(id);
    }

    public MeterHistory createMeterHistory(MeterHistory meterHistory) {
        return meterHistoryRepository.save(meterHistory);
    }

    public Optional<MeterHistory> updateMeterHistory(Integer id, MeterHistory meterHistoryDetails) {
        return meterHistoryRepository.findById(id).map(meterHistory -> {
            meterHistory.setVehicle(meterHistoryDetails.getVehicle());
            meterHistory.setMeterDate(meterHistoryDetails.getMeterDate());
            meterHistory.setMeterValue(meterHistoryDetails.getMeterValue());
            meterHistory.setMeterType(meterHistoryDetails.getMeterType());
            meterHistory.setSource(meterHistoryDetails.getSource());
            meterHistory.setVoid(meterHistoryDetails.isVoid());
            meterHistory.setVoidStatus(meterHistoryDetails.getVoidStatus());
            meterHistory.setAutoVoidReason(meterHistoryDetails.getAutoVoidReason());
            return meterHistoryRepository.save(meterHistory);
        });
    }

    public boolean deleteMeterHistory(Integer id) {
        return meterHistoryRepository.findById(id).map(meterHistory -> {
            meterHistoryRepository.delete(meterHistory);
            return true;
        }).orElse(false);
    }
}
