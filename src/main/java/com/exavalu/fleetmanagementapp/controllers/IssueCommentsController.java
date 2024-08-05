package com.exavalu.fleetmanagementapp.controllers;

import com.exavalu.fleetmanagementapp.models.IssueComments;
import com.exavalu.fleetmanagementapp.services.IssueCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/issueComments")
public class IssueCommentsController {

    @Autowired
    private IssueCommentsService issueCommentsService;

    @GetMapping
    public List<IssueComments> getAllComments() {
        return issueCommentsService.getAllComments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueComments> getCommentById(@PathVariable int id) {
        Optional<IssueComments> comment = issueCommentsService.getCommentById(id);
        if (comment.isPresent()) {
            return ResponseEntity.ok(comment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public IssueComments addComment(@RequestBody IssueComments issueComment) {
        return issueCommentsService.addComment(issueComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IssueComments> updateComment(@PathVariable int id, @RequestBody IssueComments issueCommentDetails) {
        IssueComments updatedComment = issueCommentsService.updateComment(id, issueCommentDetails);
        if (updatedComment != null) {
            return ResponseEntity.ok(updatedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable int id) {
        issueCommentsService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
