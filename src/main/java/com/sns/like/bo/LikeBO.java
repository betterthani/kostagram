package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	@Autowired
	private LikeDAO likeDAO;
	
	// 좋아요
	public void addLikeByUserIdPostId(int userId, int postId) {
		likeDAO.insertLikeByUserIdPostId(userId,postId);
	}
	
	// 좋아요 삭제
	public int deleteLikeByUserIdPostId(int userId, int postId) {
		return likeDAO.deleteLikeByUserIdPostId(userId, postId);
	}
	
	// 좋아요 했는지 조회
	public boolean getLikeByUserIdPostId(int userId, int postId) {
		return likeDAO.selectLikeByUserIdPostId(userId, postId);
	}
	
	// 좋아요 카운트
	public int getLikeByPostId(int postId) {
		return likeDAO.selectLikeByPostId(postId);
	}
	
}
