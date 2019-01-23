package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by tang ze on 2019/1/20 16:20
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project,String> {

<<<<<<< HEAD

=======
    public List<Project> findAll();

    public Project findByProjectId(String projID);
>>>>>>> master
}
