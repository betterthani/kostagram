package com.sns.follow.model;

import java.util.Date;

public class Follow {
	private int userId;
	private int follwerId;
	private Date createdAt;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFollwerId() {
		return follwerId;
	}
	public void setFollwerId(int follwerId) {
		this.follwerId = follwerId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
