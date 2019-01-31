package com.agile.crowdfunding.controller;

import com.agile.crowdfunding.entity.*;
import com.agile.crowdfunding.service.*;
import com.agile.crowdfunding.util.AuthorizationUtils;
import com.agile.crowdfunding.util.Page;
import com.agile.crowdfunding.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    MessageService messageService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    TradeService tradeService;

    @Autowired
    AuthorizationUtils auth;

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


    // 前台管理操作开始

    @RequestMapping("/pro/fore/updateState")
    public String updateForeState(Model model, String id, Integer state, HttpSession session,
                                  HttpServletRequest request) {
        if (!auth.check(session))
            return "redirect:/login/toLogin";
        projectService.updateState(id, state);
        messageService.sendMessage(id, state);

        Integer type = Integer.parseInt(request.getParameter("type"));
        String uid = (String) session.getAttribute("myId");
        List<Project> list = projectService.getProjectsByUserId(uid);
        model.addAttribute("projects", list);
        return "fore/pro/pro_frg::frg" + type;

    }
    // 前台管理操作结束

    // 后台管理操作开始
    @RequestMapping("/pro/listAllPros")
    public String listAllProjects(Model model, Page page) {
        List<Project> projects = projectService.listAllProjects(page);
        page.setTotal(projectService.total());
        model.addAttribute("projects", projects);
        model.addAttribute("page", page);
        return "back/pro/allPros";
    }

    @RequestMapping("/pro/fundingPros")
    public String fundingPros(Model model) {
        List<Project> list = projectService.getProjectsByState(21);
        model.addAttribute("projects", list);
        return "back/pro/fundingPros";
    }

    @RequestMapping("/pro/newApply")
    public String newApply(Model model) {
        List<Project> list = projectService.getProjectsByState(1);
        model.addAttribute("projects", list);
        return "back/pro/newApply";
    }

    @RequestMapping("/pro/drawApply")
    public String drawApply(Model model) {
        List<Project> list = projectService.getProjectsByState(41);
        model.addAttribute("projects", list);
        return "back/pro/drawApply";
    }

    @RequestMapping("/pro/delayApply")
    public String delayApply(Model model) {
        List<Project> list = projectService.getProjectsByState(22);
        model.addAttribute("projects", list);
        return "back/pro/delayApply";
    }

    // 新增放款
    @RequestMapping("/pro/updateState")
    public String updateState(Model model, String id, Integer state) {

        if (state == 5) {
            String userId = projectService.getProject(id).getUser().getUserId();
            Double money = projectService.getProject(id).getCurrentMoney();
            User user = userService.getById(userId);
            user.setMoney(user.getMoney() + money);
            userService.updateUser(user);
            Trade trade = new Trade();
            trade.setMoney(money);
            trade.setInfo("提款到账");
            trade.setUser(user);
            tradeService.saveTrade(trade);
        }

        projectService.updateState(id, state);
        return messageService.sendMessage(id, state);
    }

    @RequestMapping("/pro/lock")
    public String lockApply(Model model) {
        List<Project> list = projectService.getProjectsByState(99);
        List<Project> list2 = projectService.getProjectByType(80);
        list.addAll(list2);
        model.addAttribute("projects", list);
        return "back/pro/lock";
    }

    @RequestMapping("/pro/returnAll")
    public String returnAll() {
        List<Project> list = projectService.getProjectsByState(21);
        for (Project project : list) {
            returnMoney(project.getProjectId());
        }
        return "back/pro/home";
    }

    @RequestMapping("/pro/deletePro")
    public String deletePro(Model model, String id) {
        // returnMoney(id);
        // projectService.deleteProject(id);
        List<ProAndUsers> list = projectService.getProAndUsers(id);
        User user = null;
        for (ProAndUsers tmp : list) {
            String userId = tmp.getUserId();
            user = userService.getById(userId);
            Double money = user.getMoney();
            user.setMoney(money + tmp.getMoney());
            userService.updateUser(user);
            Trade trade = new Trade();
            trade.setMoney(money);
            trade.setInfo("撤销返款到账");
            trade.setUser(user);
            tradeService.saveTrade(trade);
        }
        messageService.sendMessage(id, 100);


        projectService.updateState(id, 100);
        return "back/home";
    }

    public void returnMoney(String id) {

        List<ProAndUsers> list = projectService.getProAndUsers(id);
        User user = null;

        for (ProAndUsers tmp : list) {
            String userId = tmp.getUserId();
            user = userService.getById(userId);
            Double money = user.getMoney();
            user.setMoney(money + tmp.getMoney());
            userService.updateUser(user);

        }
        projectService.updateState(id, 30);

    }

    // 后台管理操作结束
}
