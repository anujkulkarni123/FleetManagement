package com.exavalu.fleetmanagementapp.repositories;

import com.exavalu.fleetmanagementapp.models.IssueComments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueCommentsRepository extends JpaRepository<IssueComments, Integer> {
    List<IssueComments> findByIssueId(int issueId);
}