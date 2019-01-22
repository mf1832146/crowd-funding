package com.agile.crowdfunding.service;

import com.agile.crowdfunding.entity.*;

import java.util.List;

public interface ProjectService {

    public List<Project> findProjectsTop3();

    public Project getProject(String projID);

    public ProjectDetail getProjectDetail(String projID);

    public Reward getReward(String projID);

    public List<Comment> getComment(String projID);

    public List<ProAndUsers> getProAndUsers(String projID);

    public List<Image> getImages(String projID);
    //public Reward getReward(String projID);
}
