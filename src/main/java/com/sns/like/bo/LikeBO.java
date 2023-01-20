package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	@Autowired
	private LikeDAO likeDAO;
	
	public void addLikeByUserIdPostId(int userId, int postId) {
		likeDAO.insertLikeByUserIdPostId(userId,postId);
	}
}
