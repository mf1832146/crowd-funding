package com.agile.crowdfunding.service;

import com.agile.crowdfunding.entity.Message;

import java.util.List;

/**
 * Create by tang ze on 2019/1/22 15:35
 */
public interface MessageService {

    String sendMessage(String projectId,Integer state);
    void sendMessageToSupporter(String projectId,String info);
    void sendMessageToSponstor(String userId,String info);
    List<Message> getMessagesByUserId(String userId);
    void setState(String messageId);
    void deleteMsg(String messageId);
    Long totalMessage(String userId);

}
