package com.agile.crowdfunding.vo;

import com.agile.crowdfunding.util.StateUtils;

import java.sql.Date;


public class UserAndSups {
	private String userId;
	private String projectId;
	private String projectName;
	private Double targetMoney;
	private Double currentMoney;
	private Double money;
	private Integer state;
	private Date date;
	private Integer proState;
	private String orderId;
	
	//这个属性供视图view-model使用
	private String stateName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
		this.stateName = StateUtils.getStateName(state);
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getProState() {
		return proState;
	}

	public void setProState(Integer proState) {
		this.proState = proState;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStateName() {
		return stateName;
	}

	@Override
	public String toString() {
		return "UserAndSups [userId=" + userId + ", projectId=" + projectId + ", projectName=" + projectName
				+ ", targetMoney=" + targetMoney + ", currentMoney=" + currentMoney + ", money=" + money + ", state="
				+ state + ", date=" + date + ", proState=" + proState + "]";
	}

	
}
