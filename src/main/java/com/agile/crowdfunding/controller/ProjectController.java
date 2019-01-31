package com.agile.crowdfunding.controller;

import com.agile.crowdfunding.entity.*;
import com.agile.crowdfunding.service.MessageService;
import com.agile.crowdfunding.service.ProjectService;
import com.agile.crowdfunding.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class ProjectController {
    private static Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    ProjectService projectService;

    @Autowired
    private MessageService messageService;

    public void setLoginMessage(HttpSession session,Model model){
        LoginVo loginVo = (LoginVo) session.getAttribute("loginVo");
        String id = (String) session.getAttribute("myId");
        if(loginVo!= null) {
            model.addAttribute("flag", loginVo.getUsername());
            model.addAttribute("userId", id);
            model.addAttribute("messageNum", messageService.totalMessage(id));
        }
        else
            model.addAttribute("flag", "unlogin");
    }

    @RequestMapping(value = "/fore/index")
    public String toIndex(HttpSession session, Model model) {
        setLoginMessage(session,model);

        // 查找同一类型的三个项目
        List<Project> listCommonWeal = projectService.getProjectByType(1); // 查询出公益类型的项目
        model.addAttribute("commonWeal", listCommonWeal);

        List<Project> listArt = projectService.getProjectByType(2); // 查询出艺术类型的项目
        model.addAttribute("art", listArt);

        List<Project> listAgriculture = projectService.getProjectByType(3); // 查询出农业类型的项目
        model.addAttribute("agriculture", listAgriculture);

        List<Project> listPublish = projectService.getProjectByType(4); // 查询出出版类型的项目
        model.addAttribute("publish", listPublish);


        List<Project> listAmusement = projectService.getProjectByType(5); // 查询出娱乐类型的项目
        model.addAttribute("amusement", listAmusement);

        List<Project> listOther = projectService.getProjectByType(6); // 查询出其他类型的项目
        model.addAttribute("other", listOther);

        return "index";
    }

    @RequestMapping("/detail/showDetail")
    public String showDetail(String projID, Model model, HttpSession session) {
        if (projID == null) {
            projID = (String) session.getAttribute("preProject");
        }
        session.setAttribute("preProject", projID);

        setLoginMessage(session,model);

        //获取项目信息
        Project project = projectService.getProject(projID);
        //System.out.println(project.toString());
        //获取项目详细信息
        ProjectDetail projectDetail = projectService.getProjectDetail(projID);
        System.out.println(projectDetail.toString());
        //获取回报信息
        Reward reward = projectService.getReward(projID);
        System.out.println(reward.toString());
        //获取评论信息
        List<Comment> comments = projectService.getComment(projID);
        System.out.println(comments.toString());
        //获取支持信息
        List<ProAndUsers> proAndUsers = projectService.getProAndUsers(projID);
        System.out.println(proAndUsers);
        //获取图片信息
        List<Image> images = projectService.getImages(projID);
        System.out.println(images.toString());

        model.addAttribute("project", project);
        model.addAttribute("projectDetail", projectDetail);
        model.addAttribute("rewords", reward);
        model.addAttribute("comment", comments);
        model.addAttribute("support", proAndUsers);
        model.addAttribute("image", images);

        return "fore/detail_info";
    }

    @RequestMapping("/detail/submitComment")
    @ResponseBody
    public String submitComment(HttpServletResponse response, Comment comment) {
        comment.setTime(new Timestamp(System.currentTimeMillis()));
        log.info(comment.toString());
        projectService.submitComment(comment);
        return "success";
    }

    @RequestMapping("/search/searchType")
    @ResponseBody
    public String searchType(HttpSession session, String type, Model model) {
        setLoginMessage(session,model);

        SearchVo searchVo = new SearchVo();
        searchVo.setKeyWord("");
        searchVo.setSearchOrder("0");
        searchVo.setSearchState("0");
        searchVo.setSearchType(type);

        //调用ProjectService进行检索
        List<Project> list = projectService.searchProject(searchVo.getKeyWord(),
                Integer.parseInt(searchVo.getSearchType()), Integer.parseInt(searchVo.getSearchState()),
                searchVo.getSearchOrder());
        model.addAttribute("projects", list);
        model.addAttribute("init", searchVo);

        return "/fore/search";
    }
}
