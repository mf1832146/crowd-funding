package com.agile.crowdfunding.service;

import com.agile.crowdfunding.entity.*;
import com.agile.crowdfunding.vo.ProjectInfoVo;

import java.util.List;

public interface ProjectService {

    List<Project> findProjectsTop3();

    Project getProject(String projID);

    ProjectDetail getProjectDetail(String projID);

    Reward getReward(String projID);

    List<Comment> getComment(String projID);

    List<ProAndUsers> getProAndUsers(String projID);

    List<Image> getImages(String projID);
    //Reward getReward(String projID);

    void updateState(String projectId, Integer state);

    void submitComment(Comment comment);

    List<Project> getProjectsByUserId(String userID);

    List<Project> getProjectByType(Integer type);

    List<Project> searchProject(String keyWord, int type, int state, String order);

    String launchProject(Integer uid, ProjectInfoVo projectInfoVo);
  
    public void saveProAndUser(Order order);
}
