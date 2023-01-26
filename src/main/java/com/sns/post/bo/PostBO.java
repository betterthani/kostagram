package com.sns.post.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	// 글게시
	public int addPost(int userId, String userLoginId, String content, MultipartFile file) {
		
		String imagePath = null;
		if(file != null) {
			imagePath = fileManagerService.savaFile(userLoginId, file);
		}
		return postDAO.insertPost(userId, userLoginId, content, imagePath);
	}
	
	// 글 목록(타임라인)
	public List<Post> getPostList(){
		return postDAO.selectPostList();
	}
	
	// 글 한개 가져오기
	public Post getPostByPostId(int postId) {
		return postDAO.selectPostByPostId(postId);
	}
	
	// 글 삭제(회원탈퇴시)
	// input : userId
	// output: 없음
	public void deleteByUserId(int userId) {
		postDAO.deleteByUserId(userId);
		likeBO.deleteByUserId(userId);
	}
	
	// 게시글 내용 갖고오기
	public List<Post> getPostListByUserId(int userId){
		return postDAO.selectPostListByUserId(userId);
	}
	
	// 게시글 개수
	public int getPostCountByUserId(int userId) {
		return postDAO.selectPostCountByUserId(userId);
	}
	
	// 글 조회
	public Post getPostByPostIdUserId(int postId, int userId) {
		return postDAO.selectPostByPostIdUserId(postId, userId);
	}
	
	// 글 삭제
	public void deletePostByPostIdUserId(int postId, int userId) {
		// 기존 글 조회
		Post post = getPostByPostIdUserId(postId, userId);
		if(post == null) {
			logger.warn("[글 삭제] post is null. postId:{}, userId:{}",postId,userId);
			return;
		}
		
		// 이미지 있으면 삭제
		if(post.getImgPath() != null) {
			fileManagerService.deleteFile(post.getImgPath());
		}
		
		// 글 delete
		postDAO.deleteByPostIdUserId(postId, userId);
		
		// 댓글들 삭제
		commentBO.deleteByPostIdUserId(postId, userId);
		
		// 좋아요들 삭제
		likeBO.deleteByPostIdUserId(postId, userId);
	}
}
