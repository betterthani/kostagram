package com.sns.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	// 댓글 쓰기
	public int addCommentByUserIdPostIdContent(int userId, int postId, String content) {
		return commentDAO.insertCommentByUserIdPostIdContent(userId, postId, content);
	}
	
	// 댓글 조회하기
	public List<Comment> getCommentList(){
		return commentDAO.selectCommentList();
	}
}
