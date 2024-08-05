package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.IssueComments;
import com.exavalu.fleetmanagementapp.models.Issues;
import com.exavalu.fleetmanagementapp.services.IssueCommentsService;
import com.exavalu.fleetmanagementapp.services.IssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class IssuesViewController {

    @Autowired
    private IssuesService issuesService;

    @Autowired
    private IssueCommentsService issueCommentsService;

    @GetMapping("/issues/{id}")
    public String viewIssue(@PathVariable("id") int id, Model model) {
        Optional<Issues> optionalIssue = issuesService.getIssuesById(id);
        if (optionalIssue.isPresent()) {
            Issues issue = optionalIssue.get();
            List<IssueComments> comments = issueCommentsService.getCommentsByIssueId(id);
            model.addAttribute("issue", issue);
            model.addAttribute("comments", comments);
            return "issue";
        } else {
            return "issue-not-found"; // Ensure you have an issue-not-found.html page
        }
    }

    @PostMapping("/issues/{id}/comments")
    public String addComment(@PathVariable("id") int id, @RequestParam("message") String message, Model model) {
        Optional<Issues> optionalIssue = issuesService.getIssuesById(id);
        if (optionalIssue.isPresent()) {
            Issues issue = optionalIssue.get();
            IssueComments comment = new IssueComments();
            comment.setIssue(issue);
            comment.setUser("User"); // Replace with actual user
            comment.setMessage(message);
            comment.setDatePosted(new Date());
            issueCommentsService.saveComment(comment);
            return "redirect:/issues/" + id;
        } else {
            return "issue-not-found"; // Ensure you have an issue-not-found.html page
        }
    }

    @GetMapping("/issue")
    public String viewGeneralIssueModel(Model model) {
        return "issue";
    }
}
