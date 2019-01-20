package com.agile.crowdfunding.entity;

import com.alibaba.fastjson.JSONObject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Create by tang ze
 * 用户信息
 */
@Entity
@Table(name = "CROWD_FUNDING_USER")
public class User  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String userId;

    @Column(nullable = false,length = 255,unique = true)
    private String userName;

    @Column(nullable = false,length = 255)
    private String password;

    //账户余额
    @Column
    private Integer money = 0;

    @Column
    private String phone;

    @Column
    private String mail;

    public String toString(){
        return JSONObject.toJSONString(this);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
