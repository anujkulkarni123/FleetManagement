package com.exavalu.fleetmanagementapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exavalu.fleetmanagementapp.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT c FROM Comment c")
    List<Comment> findAll();
    
    @Query("SELECT c FROM Comment c WHERE c.user.id = ?1")
    List<Comment> findAllFromUser(Integer param);
}