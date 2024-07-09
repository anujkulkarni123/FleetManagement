package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.Place;
import com.exavalu.fleetmanagementapp.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Optional<Place> getPlaceById(Integer id) {
        return placeRepository.findById(id);
    }

    public Place createPlace(Place place) {
        return placeRepository.save(place);
    }

    public Optional<Place> updatePlace(Integer id, Place placeDetails) {
        return placeRepository.findById(id).map(place -> {
            place.setTeam(placeDetails.getTeam());
            place.setName(placeDetails.getName());
            place.setDescription(placeDetails.getDescription());
            place.setAddress(placeDetails.getAddress());
            return placeRepository.save(place);
        });
    }

    public boolean deletePlace(Integer id) {
        return placeRepository.findById(id).map(place -> {
            placeRepository.delete(place);
            return true;
        }).orElse(false);
    }
}
