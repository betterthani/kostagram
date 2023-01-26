package com.sns.follow.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.dao.FollowDAO;

@Service
public class FollowBO {
	@Autowired
	private FollowDAO followDAO;
	
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
	
}
