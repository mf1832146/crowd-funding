package com.agile.crowdfunding.entity;


import com.alibaba.fastjson.JSONObject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "CROWD_FUNDING_IMAGE")
public class Image {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String imageId;

    @Column
    Integer type; // 图片的类型

    @Column
    String name; // 图片名字

    @Column
    Integer projectId; // 图片对应的项目id（如果有）

    @Column
    Integer userId; // 图片对应的用户id（如果有）

    public String toString(){
        return JSONObject.toJSONString(this);
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
