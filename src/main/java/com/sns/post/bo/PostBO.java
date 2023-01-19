package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
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
}
