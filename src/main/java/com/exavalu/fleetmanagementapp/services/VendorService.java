package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.Vendor;
import com.exavalu.fleetmanagementapp.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Optional<Vendor> getVendorById(Integer id) {
        return vendorRepository.findById(id);
    }

    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public Optional<Vendor> updateVendor(Integer id, Vendor vendorDetails) {
        return vendorRepository.findById(id).map(vendor -> {
            vendor.setTeam(vendorDetails.getTeam());
            vendor.setName(vendorDetails.getName());
            vendor.setAddress(vendorDetails.getAddress());
            vendor.setNumber(vendorDetails.getNumber());
            vendor.setWebsiteURL(vendorDetails.getWebsiteURL());
            vendor.setContactName(vendorDetails.getContactName());
            vendor.setContactEmail(vendorDetails.getContactEmail());
            vendor.setLabels(vendorDetails.getLabels());
            vendor.setRating(vendorDetails.getRating());
            vendor.setWatchers(vendorDetails.getWatchers());
            return vendorRepository.save(vendor);
        });
    }

    public boolean deleteVendor(Integer id) {
        return vendorRepository.findById(id).map(vendor -> {
            vendorRepository.delete(vendor);
            return true;
        }).orElse(false);
    }
}
