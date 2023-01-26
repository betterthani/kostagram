package com.sns.user.model;

import java.util.List;

import com.sns.follow.model.Follow;
import com.sns.post.model.Post;

public class UserPage {
	// 유저 1명(기준)
	private User user;
	
	// 게시물 n개
	private List<Post> postList;
	
	// 게시물 수
	private int postCount;
	
	// 팔로워카운트
	private int followerCount;
	
	// 팔로잉카운트
	private int followeeCount;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public int getFollowerCount() {
		return followerCount;
	}

	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}

	public int getFolloweeCount() {
		return followeeCount;
	}

	public void setFolloweeCount(int followeeCount) {
		this.followeeCount = followeeCount;
	}



}
