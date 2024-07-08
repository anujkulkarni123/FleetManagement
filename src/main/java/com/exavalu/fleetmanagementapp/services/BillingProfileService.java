package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.BillingProfile;
import com.exavalu.fleetmanagementapp.repositories.BillingProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillingProfileService {

    @Autowired
    private BillingProfileRepository billingProfileRepository;

    public List<BillingProfile> getAllBillingProfiles() {
        return billingProfileRepository.findAll();
    }

    public Optional<BillingProfile> getBillingProfileById(Integer id) {
        return billingProfileRepository.findById(id);
    }

    public BillingProfile createBillingProfile(BillingProfile billingProfile) {
        return billingProfileRepository.save(billingProfile);
    }

    public Optional<BillingProfile> updateBillingProfile(Integer id, BillingProfile billingProfileDetails) {
        return billingProfileRepository.findById(id).map(billingProfile -> {
            return billingProfileRepository.save(billingProfile);
        });
    }

    public boolean deleteBillingProfile(Integer id) {
        return billingProfileRepository.findById(id).map(billingProfile -> {
            billingProfileRepository.delete(billingProfile);
            return true;
        }).orElse(false);
    }
}
