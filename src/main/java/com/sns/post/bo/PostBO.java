package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.dao.CommentDAO;
import com.sns.common.FileManagerService;
import com.sns.like.dao.LikeDAO;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private LikeDAO likeDAO;
	
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
		commentDAO.deleteByUserId(userId);
		likeDAO.deleteByUserId(userId);
	}
}
