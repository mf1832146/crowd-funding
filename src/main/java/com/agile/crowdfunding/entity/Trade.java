package com.agile.crowdfunding.entity;

import com.alibaba.fastjson.JSONObject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Create by tang ze on 2019/1/20 15:25
 * 交易记录
 */
@Entity
@Table(name = "CROWD_FUNDING_TRADE")
public class Trade implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String tradeId;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="user_id")
    private User user;

    //交易记录描述
    @Column
    private String info;

    //交易时间 默认当前时间
    @Column
    private Timestamp time = new Timestamp(System.currentTimeMillis());

    //交易金额
    @Column
    private Double money = 0.0;

    public String toString(){
       return JSONObject.toJSONString(this);
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
