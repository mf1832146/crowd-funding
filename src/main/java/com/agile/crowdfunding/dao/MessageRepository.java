package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.Message;
import com.agile.crowdfunding.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Create by tang ze on 2019/1/22 14:54
 */
public interface MessageRepository  extends JpaRepository<Message,String> {

    //根据用户id查询Message列表
    public List<Message> findMessagesByUserIdOrderByState(String userId);

    // 通过用户id查询未读消息总数 state默认是0
    public Long countByUserIdAndState(String userId,Integer state);

    // 删除一条消息
    public void deleteByMessageId(String messageId);

    // 更新消息的状态（未读->已读）
    @Query(value="UPDATE Message message SET message.state= 1 WHERE message.messageId= ?1")
    public void updateStateByMessageId(String messageId);

}
