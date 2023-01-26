package com.sns.follow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.follow.model.Follow;

@Repository
public interface FollowDAO {
	// 조회하기
	public Integer selectFollowByFollwerIdUserId(
			@Param("followerId") int followerId,
			@Param("userId") Integer userId);
		
	// 추가하기
	public void insertFollowByFollowerIdUserId(
			@Param("followerId") int followerId,
			@Param("userId") Integer userId);
	
	
	// 삭제하기
	public void deleteFollowByFollowerIdUserId(
			@Param("followerId") int followerId,
			@Param("userId") Integer userId);
	
	// 팔로우 카운트
	public int followerCountByFollowerId(int followerId);
	
	// 팔로잉 카운트
	public int followerCountByUserId(int userId);
	
}
