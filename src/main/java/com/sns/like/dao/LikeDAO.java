package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	public void insertLikeByUserIdPostId(
			@Param("userId") int userId,
			@Param("postId") int postId);
}
