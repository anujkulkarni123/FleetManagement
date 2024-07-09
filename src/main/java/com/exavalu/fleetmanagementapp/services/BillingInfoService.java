package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.BillingInfo;
import com.exavalu.fleetmanagementapp.repositories.BillingInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillingInfoService {

    @Autowired
    private BillingInfoRepository billingInfoRepository;

    public List<BillingInfo> getAllBillingInfo() {
        return billingInfoRepository.findAll();
    }

    public Optional<BillingInfo> getBillingInfoById(Integer id) {
        return billingInfoRepository.findById(id);
    }

    public BillingInfo createBillingInfo(BillingInfo billingInfo) {
        return billingInfoRepository.save(billingInfo);
    }

    public Optional<BillingInfo> updateBillingInfo(Integer id, BillingInfo billingInfoDetails) {
        return billingInfoRepository.findById(id).map(billingInfo -> {
            billingInfo.setBillingPlan(billingInfoDetails.getBillingPlan());
            billingInfo.setAddOns(billingInfoDetails.getAddOns());
            billingInfo.setBillingProfileId(billingInfoDetails.getBillingProfileId());
            billingInfo.setTeam(billingInfoDetails.getTeam());
            return billingInfoRepository.save(billingInfo);
        });
    }

    public boolean deleteBillingInfo(Integer id) {
        return billingInfoRepository.findById(id).map(billingInfo -> {
            billingInfoRepository.delete(billingInfo);
            return true;
        }).orElse(false);
    }
}
