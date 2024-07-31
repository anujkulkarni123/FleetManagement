package com.exavalu.fleetmanagementapp.services;

import com.exavalu.fleetmanagementapp.models.IssueComments;
import com.exavalu.fleetmanagementapp.repositories.IssueCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueCommentsService {

    @Autowired
    private IssueCommentsRepository issueCommentsRepository;

    public List<IssueComments> getAllComments() {
        return issueCommentsRepository.findAll();
    }

    public Optional<IssueComments> getCommentById(int id) {
        return issueCommentsRepository.findById(id);
    }

    public IssueComments addComment(IssueComments issueComment) {
        return issueCommentsRepository.save(issueComment);
    }

    public IssueComments updateComment(int id, IssueComments issueCommentDetails) {
        Optional<IssueComments> optionalComment = issueCommentsRepository.findById(id);
        if (optionalComment.isPresent()) {
            IssueComments issueComment = optionalComment.get();
            issueComment.setUser(issueCommentDetails.getUser());
            issueComment.setIssue(issueCommentDetails.getIssue());
            issueComment.setMessage(issueCommentDetails.getMessage());
            issueComment.setDatePosted(issueCommentDetails.getDatePosted());
            return issueCommentsRepository.save(issueComment);
        } else {
            return null;
        }
    }

    public void deleteComment(int id) {
        issueCommentsRepository.deleteById(id);
    }

    public List<IssueComments> getCommentsByIssueId(int issueId) {
        return issueCommentsRepository.findByIssueId(issueId);
    }
    
    public void saveComment(IssueComments comment) {
        issueCommentsRepository.save(comment);
    }
}
