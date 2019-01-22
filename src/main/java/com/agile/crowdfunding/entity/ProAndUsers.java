package com.agile.crowdfunding.entity;

import com.alibaba.fastjson.JSONObject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "CROWD_FUNDING_PROANDUSERS")
public class ProAndUsers {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String proAndUsersId;

    @Column
    private String userId;
    @Column
    private String username;
    @Column
    private String projectId;
    @Column
    private Integer money;
    @Column
    private Date date;
    @Column
    private String phone;

    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProAndUsersId() {
        return proAndUsersId;
    }

    public void setProAndUsersId(String proAndUsersId) {
        this.proAndUsersId = proAndUsersId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
