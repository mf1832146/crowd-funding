package com.agile.crowdfunding.entity;

import com.alibaba.fastjson.JSONObject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Create by tang ze on 2019/1/20 16:14
 * 众筹项目
 */
@Entity
@Table(name = "CROWD_FUNDING_PROJECT")
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String projectId;

    //项目名称
    @Column
    private String name;

    //项目发起人
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    //项目状态
    @Column
    private Integer state;

    //项目类型
    @Column
    private Integer type;

    //目标金额
    @Column
    private Double targetMoney;

    //当前募集金额
    @Column
    private Double currentMoney;

    //项目创建时间 默认当前时间
    @Column
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());

    //项目截至时间
    @Column
    private Timestamp endTime;

    //项目详情
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId", insertable = false, updatable = false)
    private ProjectDetail projectDetail;


    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Double targetMoney) {
        this.targetMoney = targetMoney;
    }

    public Double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(Double currentMoney) {
        this.currentMoney = currentMoney;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProjectDetail getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(ProjectDetail projectDetail) {
        this.projectDetail = projectDetail;
    }
}
