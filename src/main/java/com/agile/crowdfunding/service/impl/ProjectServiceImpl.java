package com.agile.crowdfunding.service.impl;

import com.agile.crowdfunding.dao.*;
import com.agile.crowdfunding.entity.*;
import com.agile.crowdfunding.exception.GlobalException;
import com.agile.crowdfunding.result.CodeMsg;
import com.agile.crowdfunding.service.ProjectService;
import com.agile.crowdfunding.util.Page;
import com.agile.crowdfunding.util.StateUtils;
import com.agile.crowdfunding.vo.ProjectInfoVo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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

    @Autowired
    UserRepository userRepository;

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
    public void saveProAndUser(Order order){
        ProAndUsers proAndUsers = new ProAndUsers();

        proAndUsers.setUsername(order.getUser().getUserName());
        proAndUsers.setUserId(order.getUser().getUserId());
        proAndUsers.setDate(order.getDate());
        proAndUsers.setMoney(order.getMoney());
        proAndUsers.setPhone(order.getUser().getPhone());
        proAndUsers.setProjectId(order.getProject().getProjectId());

        proAndUsersRepository.save(proAndUsers);
    }

    @Override
    public List<Project> listAllProjects(Page page) {
        return projectRepository.listProjects(page.getStart(),page.getCount());
    }

    @Override
    public Integer total() {
        return projectRepository.findAll().size();
    }

    @Override
    public List<Project> getProjectsByState(Integer state) {
        return projectRepository.getProjectsByState(state);
    }

    @Override
    public void submitComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Project> getProjectsByUserId(String userID) {

        List<Project> projects = projectRepository.getProjectsByUserUserId(userID);
        for (int i = 0; i < projects.size(); i++) {
            projects.get(i).setStateName(StateUtils.getStateName(projects.get(i).getState()));
        }
        return projects;
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

    @Override
    public String launchProject(String uid, ProjectInfoVo projectInfoVo) {
        if (projectInfoVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        org.slf4j.Logger log = LoggerFactory.getLogger(ProjectService.class);

        // PROJECT
        /////////////////////////
        Project newProject = new Project();
        newProject.setName(projectInfoVo.getProTitle());
        User tmpUser = userRepository.findFirstByUserId(uid);
        newProject.setUser(tmpUser);
        newProject.setState(1);
        newProject.setType(1);
        newProject.setTargetMoney(projectInfoVo.getProTargetMoney());
        Timestamp sqlTime = new Timestamp(System.currentTimeMillis());
        newProject.setCreateTime(sqlTime);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, projectInfoVo.getProLastTime());
        sqlTime = new Timestamp(System.currentTimeMillis());
        newProject.setEndTime(sqlTime);
        projectRepository.save(newProject);
        log.info(newProject.getProjectId());

        // PROJECT_DETAIL
        /////////////////////////
        ProjectDetail newProjectDetail = new ProjectDetail();
        newProjectDetail.setProjectId(newProject.getProjectId());
        newProjectDetail.setName(projectInfoVo.getProName());
        newProjectDetail.setPhone(projectInfoVo.getProPhoneNumber());

        newProjectDetail.setTitle(projectInfoVo.getProTitle());
        newProjectDetail.setPurpose(projectInfoVo.getProPurpose());
        newProjectDetail.setLocation(projectInfoVo.getProLocation());
        newProjectDetail.setLabels(projectInfoVo.getProLabels());
        newProjectDetail.setCoverStory(projectInfoVo.getProCoverStory());

        projectDetailRepository.save(newProjectDetail);

        // RETURN_LEVEL
        /////////////////////////
        Reward newReturnLevel = new Reward();
        newReturnLevel.setProjectId(newProject.getProjectId());
        newReturnLevel.setReturnType(""+projectInfoVo.getProTypeOfReward());
        newReturnLevel.setOrderMoney(projectInfoVo.getProAmountForReward());
        newReturnLevel.setReturnDetail(projectInfoVo.getProReward());
        //修改
        rewardRepository.save(newReturnLevel);
        return newProject.getProjectId();
    }
}
