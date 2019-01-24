package com.agile.crowdfunding.vo;

public class OrderDetailsVO {
	private String projectId;
	private String userId;
	private Double money;
	private String phone;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "OrderDetailsVO [projectId=" + projectId + ", userId=" + userId + ", money=" + money + ", phone=" + phone
				+ "]";
	}
	 
	
}
