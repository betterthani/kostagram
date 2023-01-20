package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	public void insertLikeByUserIdPostId(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	
	public int deleteLikeByUserIdPostId(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	public boolean selectLikeByUserIdPostId(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	public int selectLikeByPostId(int postId);
	
}
