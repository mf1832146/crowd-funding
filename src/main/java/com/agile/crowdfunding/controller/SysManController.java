package com.agile.crowdfunding.controller;

import com.agile.crowdfunding.entity.Message;
import com.agile.crowdfunding.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class SysManController {

    @Autowired
    MessageService messageSevice;

    @RequestMapping("/updateMsg")
    //更新系统的消息
    public String updateMsg(HttpSession session, HttpServletRequest request, String id, Model model) {
        messageSevice.setState(id);

        Integer type = Integer.parseInt(request.getParameter("type"));

        String uid = (String) session.getAttribute("myId");
        List<Message> messages = messageSevice.getMessagesByUserId(uid);
        model.addAttribute("messages", messages);
        return "fore/pro/msg_frg::frg" + type;
    }
    //删除系统消息
    @RequestMapping("/deleteMsg")
    public String  deleteMsg(HttpSession session,HttpServletRequest  request,String id,Model model) {

        if(id != null)
            messageSevice.deleteMsg(id);

        Integer type = Integer.parseInt(request.getParameter("type"));

        String uid = (String) session.getAttribute("myId");
        List<Message> messages = messageSevice.getMessagesByUserId(uid);
        model.addAttribute("messages", messages);
        return "fore/pro/msg_frg::frg" + type;
    }
}
