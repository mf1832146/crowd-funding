package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by tang ze on 2019/1/20 16:20
 */
public interface ProjectRepository extends JpaRepository<Project,String> {
}
