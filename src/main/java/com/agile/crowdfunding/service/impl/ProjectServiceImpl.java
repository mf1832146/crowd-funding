package com.agile.crowdfunding.service.impl;

import com.agile.crowdfunding.dao.*;
import com.agile.crowdfunding.entity.*;
import com.agile.crowdfunding.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    RewardRepository rewardRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ProjectDetailRepository projectDetailRepository;

    @Autowired
    ProAndUsersRepository proAndUsersRepository;

    @Autowired
    ImageRepository imageRepository;

    @Override
    public List<Project> findProjectsTop3() {
        List<Project> projects = projectRepository.findAll();

        List<Project> projectsTop3 = new ArrayList<Project>();
        projectsTop3.add(projects.get(0));
        projectsTop3.add(projects.get(1));
        projectsTop3.add(projects.get(2));

        return projectsTop3;
    }

    @Override
    public Project getProject(String projID) {
        return projectRepository.findByProjectId(projID);
    }

    @Override
    public ProjectDetail getProjectDetail(String projID) {
        return projectDetailRepository.findByProjectId(projID);
    }

    @Override
    public Reward getReward(String projID) {
        return rewardRepository.findByProjectId(projID);
    }

    @Override
    public List<Comment> getComment(String projID) {
        return commentRepository.findByProjectId(projID);
    }

    @Override
    public List<ProAndUsers> getProAndUsers(String projID) {
        return proAndUsersRepository.findByProjectId(projID);
    }

    @Override
    public List<Image> getImages(String projID) {
        return imageRepository.findByProjectId(projID);
    }

    @Override
    public void updateState(String projectId, Integer state) {
        projectRepository.updateStateByProjectId(projectId, state);
    }

    @Override
    public void submitComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Project> getProjectsByUserId(String userID) {
        return projectRepository.getProjectsByUserUserId(userID);
    }

    @Override
    public List<Project> getProjectByType(Integer type) {
        return projectRepository.findFirst3ByType(type);
    }

    @Override
    public List<Project> searchProject(String keyWord, int type, int state, String order) {
        keyWord = '%' + keyWord + '%';
        return projectRepository.findByNameContainingAndTypeAndState(keyWord, type, state);
    }
}
