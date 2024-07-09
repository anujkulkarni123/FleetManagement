package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.ShopDirectory;
import com.exavalu.fleetmanagementapp.repositories.ShopDirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopDirectoryService {

    @Autowired
    private ShopDirectoryRepository shopDirectoryRepository;

    public List<ShopDirectory> getAllShopDirectories() {
        return shopDirectoryRepository.findAll();
    }

    public Optional<ShopDirectory> getShopDirectoryById(Integer id) {
        return shopDirectoryRepository.findById(id);
    }

    public ShopDirectory createShopDirectory(ShopDirectory shopDirectory) {
        return shopDirectoryRepository.save(shopDirectory);
    }

    public Optional<ShopDirectory> updateShopDirectory(Integer id, ShopDirectory shopDirectoryDetails) {
        return shopDirectoryRepository.findById(id).map(shopDirectory -> {
            shopDirectory.setShopName(shopDirectoryDetails.getShopName());
            shopDirectory.setInNetwork(shopDirectoryDetails.isInNetwork());
            shopDirectory.setGoogleId(shopDirectoryDetails.getGoogleId());
            shopDirectory.setVerificationStatus(shopDirectoryDetails.getVerificationStatus());
            shopDirectory.setOwnership(shopDirectoryDetails.getOwnership());
            shopDirectory.setFeatures(shopDirectoryDetails.getFeatures());
            return shopDirectoryRepository.save(shopDirectory);
        });
    }

    public boolean deleteShopDirectory(Integer id) {
        return shopDirectoryRepository.findById(id).map(shopDirectory -> {
            shopDirectoryRepository.delete(shopDirectory);
            return true;
        }).orElse(false);
    }
}
