package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.ProjectDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectDetailRepository extends JpaRepository<ProjectDetail, String> {

    public ProjectDetail findByProjectId(String projectId);

}
