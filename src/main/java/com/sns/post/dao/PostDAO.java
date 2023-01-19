package com.sns.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.model.Post;

@Repository
public interface PostDAO {
	
	// test용
	public List<Map<String,Object>> selectPostListTest();
	
	// 글쓰기
	public int insertPost(
			@Param("userId") int userId, 
			@Param("userLoginId") String userLoginId, 
			@Param("content") String content, 
			@Param("imgPath") String imgPath);
	
	// 글목록
	public List<Post> selectPostByUserId(int userId);
}
