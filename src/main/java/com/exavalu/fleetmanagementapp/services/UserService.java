package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.User;
import com.exavalu.fleetmanagementapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Integer id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setEmail(userDetails.getEmail());
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setVolumeUnit(userDetails.getVolumeUnit());
            user.setPageItems(userDetails.getPageItems());
            user.setWatchingEquipment(userDetails.getWatchingEquipment());
            user.setVehicles(userDetails.getVehicles());
            user.setTeam(userDetails.getTeam());
            return userRepository.save(user);
        });
    }

    public boolean deleteUser(Integer id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }
}
