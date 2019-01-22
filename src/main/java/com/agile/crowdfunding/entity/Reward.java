package com.agile.crowdfunding.entity;


import com.alibaba.fastjson.JSONObject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "CROWD_FUNDING_REWARD")
public class Reward {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String rewardId;

    @Column
    private String projectId;

    @Column
    private String returnType;
    @Column
    private int orderMoney;
    @Column
    private String returnDetail;

    public String toString(){
        return JSONObject.toJSONString(this);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        switch (returnType) {
            case "1":
                this.returnType = "实物回报";
                break;
            case "2":
                this.returnType = "虚拟回报";
                break;
            default:
                this.returnType = "其他回报";
        }
    }

    public int getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(int orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getReturnDetail() {
        return returnDetail;
    }

    public void setReturnDetail(String returnDetail) {
        this.returnDetail = returnDetail;
    }
}
