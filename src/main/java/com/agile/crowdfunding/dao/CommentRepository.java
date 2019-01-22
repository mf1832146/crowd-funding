package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

    public List<Comment> findByProjectId(String projectId);
}
