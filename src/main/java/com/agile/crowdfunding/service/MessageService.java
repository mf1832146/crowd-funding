package com.agile.crowdfunding.service;

import com.agile.crowdfunding.entity.Message;

import java.util.List;

/**
 * Create by tang ze on 2019/1/22 15:35
 */
public interface MessageService {

    public String sendMessage(String projectId,Integer state);
    public void sendMessageToSupporter(String projectId,String info);
    public void sendMessageToSponstor(String userId,String info);
    public List<Message> getMessagesByUserId(String userId);
    public void setState(String messageId);
    public void deleteMsg(String messageId);
    public Long totalMessage(String userId);

}
