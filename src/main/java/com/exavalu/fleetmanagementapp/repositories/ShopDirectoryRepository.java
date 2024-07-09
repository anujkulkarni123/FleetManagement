package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.ShopDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopDirectoryRepository extends JpaRepository<ShopDirectory, Integer> {
    // You can define custom query methods here if needed
}
