package com.exavalu.fleetmanagementapp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exavalu.fleetmanagementapp.models.Comment;
import com.exavalu.fleetmanagementapp.repositories.CommentRepository;

@Service
public class CommentService {
	// we dont need a controller for comments...
	@Autowired
	private CommentRepository commentRepository;

	// get all comments
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}
	
	// get all recent comments
	public List<Comment> getAllRecentComments() {
		ArrayList<Comment> recentComments = new ArrayList<>();
		List<Comment> allComments = getAllComments();
		LocalDate currentDate = LocalDate.now();
		LocalDate weekAgoDate = currentDate.minusDays(7);
		
		for (Comment c : allComments) {
			if (c.getDate().isAfter(weekAgoDate)) recentComments.add(c);
			else if (c.getDate().isEqual(weekAgoDate)) recentComments.add(c);
		}
		
		List<Comment> recentListofComments = recentComments;
		return recentListofComments;
	}
	
	// get all comments specified to a given userId
	public List<Comment> getAllCommentsFromUser(Integer userId) {
		return commentRepository.findAllFromUser(userId);
	}
	
	public Comment createComment(Comment comment) {
		return commentRepository.save(comment);
	}
 
}
