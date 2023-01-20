package com.sns.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;

@Repository
public interface CommentDAO {

	public int insertCommentByUserIdPostIdContent(
			@Param("userId") int userId,
			@Param("postId") int postId, 
			@Param("content") String content);
	
	public List<Comment> selectCommentListByPostId(int postId);
	
	public List<CommentView> generateCommentViewListByPostId(int postId);
	
}
