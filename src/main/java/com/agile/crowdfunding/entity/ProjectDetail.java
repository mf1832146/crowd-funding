package com.agile.crowdfunding.entity;

import com.alibaba.fastjson.JSONObject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Create by tang ze on 2019/1/20 16:21
 * 项目详情
 */
@Entity
@Table(name = "CROWD_FUNDING_PROJECT_DETAIL")
public class ProjectDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String projectDetailId;

    //项目id
    @Column
    private String projectId;

    //项目发起人
    @Column
    private String name;

    //项目发起人电话
    @Column
    private String phone;

    //项目标题
    @Column
    private String title;

    //项目目的
    @Column
    private String purpose;

    //项目标签
    @Column
    private String labels;

    //项目所在地
    @Column
    private String location;

    //项目封面故事
    @Column
    private String coverStory;

    //项目封面图片
    @Column
    private String cover;

    //项目类型
    @Column
    private Integer type;

    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public String getProjectDetailId() {
        return projectDetailId;
    }

    public void setProjectDetailId(String projectDetailId) {
        this.projectDetailId = projectDetailId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCoverStory() {
        return coverStory;
    }

    public void setCoverStory(String coverStory) {
        this.coverStory = coverStory;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = "/springUpload/coverPhoto/pid_" + projectDetailId + "/" + cover;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
