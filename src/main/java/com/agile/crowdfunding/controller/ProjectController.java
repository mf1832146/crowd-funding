package com.agile.crowdfunding.controller;

import com.agile.crowdfunding.entity.*;
import com.agile.crowdfunding.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "index")
    public String findProjectsTop3() {
        return "index";
    }

    @RequestMapping("/detail/showDetail")
    public String showDetail(Integer projID, Model model, HttpSession session) {
        if (projID == null) {
            projID = (Integer) session.getAttribute("preProject");
        }
        session.setAttribute("preProject", projID);

        //1.获取项目信息
        ProjectDetail projectDetail = projectService.getProjectDetail(projID.toString());
        System.out.println(projectDetail.toString());
        //2.获取回报信息
        Reward reward = projectService.getReward(projID.toString());
        //3.获取评论信息
        List<Comment> comments = projectService.getComment(projID.toString());
        //4.获取支持信息
        List<ProAndUsers> proAndUsers = projectService.getProAndUsers(projID.toString());
        //5.获取图片信息
        List<Image> images = projectService.getImages(projID.toString());

        model.addAttribute("project", projectDetail);
        model.addAttribute("rewords", reward);
        model.addAttribute("comment", comments);
        model.addAttribute("support", proAndUsers);
        model.addAttribute("image", images);

        return "/fore/detail_info";
    }

}
