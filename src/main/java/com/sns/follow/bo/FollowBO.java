package com.sns.follow.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.dao.FollowDAO;
import com.sns.follow.model.Follow;
import com.sns.post.model.FollowerUser;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class FollowBO {
	@Autowired
	private FollowDAO followDAO;
	
	@Autowired
	private UserBO userBO;
	
	public void follwToggle(int followerId, Integer userId) {
		// select db
		if (followDAO.selectFollowByFollwerIdUserId(followerId, userId) != null) {
			// 조회 있으면 delete db
			followDAO.deleteFollowByFollowerIdUserId(followerId, userId);
		} else {
			// 조회 없으면 insert db
			followDAO.insertFollowByFollowerIdUserId(followerId, userId);
		}
	}

	// 팔로우 카운트
	public int followerCountByFollowerId(int followerId) {
		return followDAO.followerCountByFollowerId(followerId);
	}

	// 팔로잉 카운트
	public int followerCountByUserId(Integer userId) {
		return followDAO.followerCountByUserId(userId);
	}

	// 팔로우 여부
	public boolean existFollow(int followerId, Integer userId) {
		if (userId == null) {
			return false;
		}
		return followDAO.selectFollowByFollwerIdUserId(followerId, userId) != null ? true : false;
	}
	
	// 팔로잉 조회
	public List<Follow> getFolloweeListByUserId(int userId){
		return followDAO.selectFolloweeListByUserId(userId);
	}
	
	// 팔로워 조회
	public List<Follow> getFollowerListByFollowerId(int followerId){
		return followDAO.selectFollowerListByFollowerId(followerId);
	}
	
	// 팔로워 유저 엮기
	//input : followerId
	public List<FollowerUser> generateFollowerUserListByFollowerId(int followerId){
		List<FollowerUser> followerUserList = new ArrayList<>();
		
		// 유저 리스트
		List<Follow> followList = getFollowerListByFollowerId(followerId);
		for(Follow follow : followList) {
			FollowerUser followerUser = new FollowerUser();
			
			// 팔로우 정보
			followerUser.setFollow(follow);
			
			// 유저 정보
			User user = userBO.getUserByUserId(follow.getUserId());
			followerUser.setUser(user);
			
			followerUserList.add(followerUser);
		}
		
		return followerUserList;
	}
	
	// 팔로잉 유저 엮기 
	// input : userId
	public List<FollowerUser> generateFolloweeUserListByUserId(int userId){
		List<FollowerUser> followerUserList = new ArrayList<>();
		
		// 유저 리스트
		List<Follow> followList = getFolloweeListByUserId(userId);
		for(Follow follow : followList) {
			FollowerUser followerUser = new FollowerUser();
			
			// 팔로우 정보
			followerUser.setFollow(follow);
			
			// 유저 정보
			followerUser.setUser(userBO.getUserByUserId(follow.getFollowerId()));
			
			followerUserList.add(followerUser);
		}
		
		return followerUserList;
	}
	
}
