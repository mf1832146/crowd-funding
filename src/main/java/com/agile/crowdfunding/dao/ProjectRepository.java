package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by tang ze on 2019/1/20 16:20
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project,String> {
    @Query(value ="select * from crowd_funding_project c "
            + "limit ?1,?2 ",nativeQuery = true)
    List<Project> listProjects(int start, int num);

    Project findByProjectId(String projID);

    @Modifying
    @Query(value="UPDATE Project project SET project.state= ?2 WHERE project.projectId= ?1")
    void updateStateByProjectId(String projectId,Integer state);

    List<Project> getProjectsByUserUserId(String userId);

    List<Project> findFirst3ByType(Integer type);

    List<Project> findByNameContainingAndTypeAndState(String name, int type, int state);

    List<Project> findAll();

    List<Project> getProjectsByState(Integer state);

}
