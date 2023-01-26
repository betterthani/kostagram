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
	
	/*
	public boolean existLike(
			@Param("userId") int userId, // dao에서는 반드시 있다라는 가정
			@Param("postId") int postId);
	
	public int selectLikeCountByPostId(int postId);
	*/
	
	// 글 삭제(회원탈퇴시)
	public void deleteByUserId(int userId);
	
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);
	
	// 글 삭제(타임라인)
	public void deleteByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
}
