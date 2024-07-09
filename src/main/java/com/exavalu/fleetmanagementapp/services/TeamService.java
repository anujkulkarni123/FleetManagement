package com.exavalu.fleetmanagementapp.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exavalu.fleetmanagementapp.models.Team;
import com.exavalu.fleetmanagementapp.repositories.TeamRepository;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Integer id) {
        try {
            return teamRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Team not found"));
        } catch (NoSuchElementException e) {
            // Handle the exception, e.g., log it or rethrow it
            throw e;
        }
    }


    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Optional<Team> updateTeam(Integer id, Team teamDetails) {
        return teamRepository.findById(id).map(team -> {
            team.setAccountOwner(teamDetails.getAccountOwner());
            team.setVehicles(teamDetails.getVehicles());
            team.setAccountName(teamDetails.getAccountName());
            team.setLogo(teamDetails.getLogo());
            team.setAddress(teamDetails.getAddress());
            team.setCity(teamDetails.getCity());
            team.setState(teamDetails.getState());
            team.setZipCode(teamDetails.getZipCode());
            team.setCountry(teamDetails.getCountry());
            team.setNumber(teamDetails.getNumber());
            team.setCurrency(teamDetails.getCurrency());
            team.setDateFormat(teamDetails.getDateFormat());
            team.setTimeZone(teamDetails.getTimeZone());
            team.setUsageUnit(teamDetails.getUsageUnit());
            team.setVolumeUnit(teamDetails.getVolumeUnit());
            team.setMeasurementSystem(teamDetails.getMeasurementSystem());
            team.setTax1(teamDetails.getTax1());
            team.setTax2(teamDetails.getTax2());
            team.setTaxfree(teamDetails.isTaxfree());
            team.setSecondaryTax(teamDetails.getSecondaryTax());
            team.setDefaultTax(teamDetails.getDefaultTax());
            team.setCompanySize(teamDetails.getCompanySize());
            team.setFleetAssets(teamDetails.getFleetAssets());
            team.setIndustries(teamDetails.getIndustries());
            team.setAdminRole(teamDetails.getAdminRole());
            team.setAdminExperience(teamDetails.getAdminExperience());
            return teamRepository.save(team);
        });
    }

    public boolean deleteTeam(Integer id) {
        return teamRepository.findById(id).map(team -> {
            teamRepository.delete(team);
            return true;
        }).orElse(false);
    }
}
