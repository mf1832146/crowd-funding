package com.agile.crowdfunding.entity;

/*
 * 回报分级的实体类
 */
public class ReturnLevel {
    private String id;
    private String projectId;
    private Integer returnType;
    private double orderMoney;
    private String returnDetail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getReturnDetail() {
        return returnDetail;
    }

    public void setReturnDetail(String returnDetail) {
        this.returnDetail = returnDetail;
    }

    @Override
    public String toString() {
        return "ReturnLevel [id=" + id + ", projectId=" + projectId + ", returnType=" + returnType + ", orderMoney="
                + orderMoney + ", returnDetail=" + returnDetail + "]";
    }

}
