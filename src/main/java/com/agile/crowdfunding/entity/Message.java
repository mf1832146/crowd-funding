package com.agile.crowdfunding.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Create by tang ze on 2019/1/22 14:50
 */
@Entity
@Table(name="CROWD_FUNDING_MESSAGE")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String messageId;

    @Column
    private String userId;

    @Column
    private String info;

    //默认当前时间
    @Column
    private Date time = new Date(System.currentTimeMillis());

    @Column
    private Integer state = 0;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
