package com.sns.post.model;

import com.sns.follow.model.Follow;
import com.sns.user.model.User;

public class FollowerUser {
	
	// 팔로우
	private Follow follow;
	
	// 유저 이름 위해 유저
	private User user;
	
	public Follow getFollow() {
		return follow;
	}
	public void setFollow(Follow follow) {
		this.follow = follow;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
