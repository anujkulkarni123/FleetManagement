package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.Issues;
import com.exavalu.fleetmanagementapp.repositories.IssuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IssuesService {

    @Autowired
    private IssuesRepository issuesRepository;

    public List<Issues> getAllIssues() {
        return issuesRepository.findAll();
    }

    public Optional<Issues> getIssuesById(Integer id) {
        return issuesRepository.findById(id);
    }

    public Issues createIssues(Issues issues) {
        return issuesRepository.save(issues);
    }

    public Optional<Issues> updateIssues(Integer id, Issues issuesDetails) {
        return issuesRepository.findById(id).map(issues -> {
//            issues.setPriority(issuesDetails.getPriority());
//            issues.setVehicle(issuesDetails.getVehicle());
//            issues.setAssetType(issuesDetails.getAssetType());
//            issues.setIssueNumber(issuesDetails.getIssueNumber());
//            issues.setSummary(issuesDetails.getSummary());
//            issues.setStatus(issuesDetails.getStatus());
//            issues.setSource(issuesDetails.getSource());
//            issues.setReportedDate(issuesDetails.getReportedDate());
//            issues.setAssignedUser(issuesDetails.getAssignedUser());
//            issues.setLabels(issuesDetails.getLabels());
//            issues.setWatchers(issuesDetails.getWatchers());
//            issues.setTeam(issuesDetails.getTeam());
//            issues.setService(issuesDetails.getService());
            return issuesRepository.save(issues);
        });
    }
    
    // dont have a duedate column right now, currently set 7 days to due date as considered late
    public List<Issues> getAllIntervalIssues(Integer days) {
    	ArrayList<Issues> lateIssues = new ArrayList<>();
    	List<Issues> allIssues = getAllIssues();
    	LocalDateTime now = LocalDateTime.now();
        LocalDateTime sevenDaysAgo = now.minus(days, ChronoUnit.DAYS);

    	for (Issues issue : allIssues) {
            if (issue.getReportedDate().isBefore(sevenDaysAgo)) {
                lateIssues.add(issue);
            }
        }
    	System.out.println(lateIssues.size());
    	return lateIssues;
    }
    

    public boolean deleteIssues(Integer id) {
        return issuesRepository.findById(id).map(issues -> {
            issuesRepository.delete(issues);
            return true;
        }).orElse(false);
    }
}
