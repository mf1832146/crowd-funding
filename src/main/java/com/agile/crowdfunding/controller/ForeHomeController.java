package com.agile.crowdfunding.controller;

import com.agile.crowdfunding.entity.Message;
import com.agile.crowdfunding.entity.Project;
import com.agile.crowdfunding.entity.Trade;
import com.agile.crowdfunding.entity.User;
import com.agile.crowdfunding.service.*;
import com.agile.crowdfunding.util.AuthorizationUtils;
import com.agile.crowdfunding.vo.UserAndSups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/fore")
public class ForeHomeController {
    @Autowired
    AuthorizationUtils auth;
    @Autowired
    UserService userService;
    @Autowired
    ProjectService projectService;
    @Autowired
    OrderService orderService;
    @Autowired
    MessageService messageService;
    @Autowired
    TradeService tradeService;

    @RequestMapping("/home")
    public String home(Model model, HttpSession session) {

        if (!auth.check(session))
            return "redirect:/login/toLogin";

        String id = (String) session.getAttribute("myId");
        User user = userService.getById(id);
        model.addAttribute("user", user);

        List<UserAndSups> orders = orderService.getOrdersByUserId(id);
        model.addAttribute("orders", orders);

        List<Project> projects = projectService.getProjectsByUserId(id);
        model.addAttribute("projects", projects);

        List<Message> messages = messageService.getMessagesByUserId(id);
        model.addAttribute("messages", messages);

        List<Trade> trades = tradeService.getTrades(id);
        model.addAttribute("trades", trades);

        return "fore/usercenter";
    }

    //删除订单（通过id）
    @RequestMapping("/deleteOrder")
    public String deleteOrders(HttpServletRequest request, Model model, HttpSession session, String id) {
        String type = request.getParameter("type");
        System.out.println(type);
        orderService.deleteOrder(id);
        String uid = (String) session.getAttribute("myId");
        List<UserAndSups> orders = orderService.getOrdersByUserId(uid);
        model.addAttribute("orders",orders);
        return "fore/order/order_frg::frg" + type;
    }
}
