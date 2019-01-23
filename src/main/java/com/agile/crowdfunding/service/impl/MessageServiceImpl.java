package com.agile.crowdfunding.service.impl;

import com.agile.crowdfunding.dao.MessageRepository;
import com.agile.crowdfunding.dao.UserRepository;
import com.agile.crowdfunding.entity.Message;
import com.agile.crowdfunding.entity.ProAndUsers;
import com.agile.crowdfunding.entity.Project;
import com.agile.crowdfunding.entity.User;
import com.agile.crowdfunding.service.MessageService;
import com.agile.crowdfunding.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by tang ze on 2019/1/22 14:55
 */
@Service
public class MessageServiceImpl implements MessageService {
    private static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserRepository userRepository;

    public String sendMessage(String projectId,Integer state) {
        String infoToSpon = null;
        String infoToSup = null;
        String url = null;
        Project project = projectService.getProject(projectId);
        Double currentMoney = project.getCurrentMoney();
        String userId = project.getUser().getUserId();
        switch (state) {
            case 20:
                infoToSpon = "【审核失败】您发起的项目 " + project.getName() + " 因违法、违规或其他原因未能通过审核，请修改后重新提交";
                url =  "redirect:/pro/newApply";
                break;
            case 21:
                infoToSpon = "【审核通过】您发起的项目 " + project.getName() + " 已通过审核! 要想上首页please ***";
                url = "redirect:/pro/newApply";
                break;
            case 31:
                infoToSpon = "【审核通过】您发起的项目 " + project.getName() + " 已被解锁! 要想上首页please ****";
                url = "redirect:/pro/newApply";
                break;

            case 5:
                infoToSpon = "【申请通过】您的提款申请通过审核，金额为 " + currentMoney;
                infoToSup = "您支持的项目 " + project.getName() + " 已提款，将进入回报阶段,敬请期待 ";
                url = "redirect:/pro/drawApply";
                break;
            case 40:
                infoToSpon = "【申请失败】您的提款申请未能通过审核，如有问题请联系XXXXXXXX ";
                url = "redirect:/pro/drawApply";
                break;
            case 99:
                infoToSpon = "【项目锁定】您发起的项目 " + project.getName() + " 因违法、违规或其他原因被锁定，如有问题请联系XXXXXXXX";
                infoToSup = "【项目锁定】您支持的项目 " + project.getName() + " 因违法、违规或其他原因被锁定，如有问题请联系XXXXXXXX";
                url = "redirect:/pro/listAllPros";
            case 100:
                infoToSpon = "【项目撤销】您发起的项目 " + project.getName() + " 因违法、违规或其他原因被撤销，如有问题请联系XXXXXXXX";
                infoToSup = "【项目撤销】您支持的项目 " + project.getName() + " 因违法、违规或其他原因被撤销，如有问题请联系XXXXXXXX";
                url = "redirect:/pro/listAllPros";
                break;
            //以上均为后台操作

            //以下为前台操作
            case 6:
                infoToSup = "【项目回报】您支持的项目 " + project.getName() + " 已回报，请及时确认回报，如有问题请联系XXXXXXXX ";
                url = "redirect:/pro/fore/listMyPros";
                break;
            case 1:
                url = "redirect:/pro/fore/listMyPros";
                break;
            case 41:
                url = "redirect:/pro/fore/listMyPros";
                break;
            default:
                break;
        }
        //不为空就发消息
        if(infoToSpon != null) {
            sendMessageToSponstor(userId,infoToSpon);
        }
        if(infoToSup != null) {
            sendMessageToSupporter(projectId,infoToSup);
        }
        return url;
    }
    public void sendMessageToSupporter(String projectId,String info) {
        List<ProAndUsers> lists =  projectService.getProAndUsers(projectId);
        for (ProAndUsers tmp : lists) {
            Message message = new Message();

            message.setInfo(info);
            message.setUserId(tmp.getUserId());
            messageRepository.save(message);
        }
    }
    public void sendMessageToSponstor(String userId,String info) {
        Message message = new Message();
        message.setInfo(info);
        User user = userRepository.findFirstByUserId(userId);
        message.setUserId(userId);
        messageRepository.save(message);
    }
    public List<Message> getMessagesByUserId(String userId) {
        return messageRepository.findMessagesByUserIdOrderByState(userId);
    }
    public void setState(String messageId) {
        messageRepository.updateStateByMessageId(messageId);
    }
    public void deleteMsg(String messageId) {
        messageRepository.deleteByMessageId(messageId);
    }

    public Long totalMessage(String userId){
        return messageRepository.countByUserIdAndState(userId,0);
    }



}
