package com.agile.crowdfunding.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agile.crowdfunding.entity.Project;
import com.agile.crowdfunding.entity.User;
import com.agile.crowdfunding.service.OrderService;
import com.agile.crowdfunding.service.ProjectService;
import com.agile.crowdfunding.service.UserService;
import com.agile.crowdfunding.util.AuthorizationUtils;
import com.agile.crowdfunding.util.Page;
import com.agile.crowdfunding.vo.UserAndSups;

@Controller
@RequestMapping("/user")
// 表示所有方法以 /user 为父路经
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ProjectService projectService;
    @Autowired
    OrderService orderService;
    @Autowired
    AuthorizationUtils auth;

    // 注意区别 这里是前台操作 查看自己 信息 通过session获得
    @RequestMapping("/fore/myDetails")
    public String getMyDetails(Model model, HttpSession session) {
        if (!auth.check(session))
            return "redirect:/login/toLogin";
        String id = (String) session.getAttribute("myId");
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "fore/user/myDetails";
    }

    // 注意区别 这里是前台操作 查看其他用户信息，如：发起项目 ，他人参与的项目不公开
    @RequestMapping("/fore/userDetails")
    public String getUserDetails(Model model, String id, HttpSession session) {
        if (!auth.check(session))
            return "redirect:/login/toLogin";
        User user = userService.getById(id);
        model.addAttribute("user", user);
        List<Project> projects = projectService.getProjectsByUserId(id);
        model.addAttribute("projects", projects);
        return "fore/user/userDetails";
    }

    // 以上为前台管理

    // 以下为后台管理

    //列出所有的用户
    @RequestMapping("/back/listUsers")
    public String listUser(Model model, Page page) {
        // List<User> users = userService.listUsers(page);
        List<User> users = userService.listUsers(page);
        page.setTotal(userService.total());
        model.addAttribute("users", users);
        model.addAttribute("page", page);
        return "back/user/listUsers";
    }

    //删除用户
    @RequestMapping("/back/deleteUser")
    public String deleteUser(String id) {
        userService.deleteById(id);
        return "redirect:/user/back/listUsers";
    }
    //新增用户
    @RequestMapping("/back/editUser")
    public String editUser(Model model, String id) {
        model.addAttribute("fuckuser", userService.getById(id));
        return "back/user/editUser";
    }
    //修改用户
    @RequestMapping("/back/updateUser")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/user/back/listUsers";
    }
    //查看用户详情
    @RequestMapping("/back/userDetails")
    public String userDetails(Model model, String id) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        List<Project> projects = projectService.getProjectsByUserId(id);
        model.addAttribute("projects", projects);
        List<UserAndSups> orders = orderService.getOrdersByUserId(id);
        model.addAttribute("orders", orders);
        return "back/user/userDetails";
    }

}
