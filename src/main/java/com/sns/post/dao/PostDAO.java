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
	public List<Post> selectPostList();
	
	// 게시글 내용 갖고오기
	public List<Post> selectPostListByUserId(int userId);
	
	// 게시글 개수
	public int selectPostCountByUserId(int userId);

	// 글 한개 가져오기(글 상세)
	public Post selectPostByPostId(int postId);

	// 글 전체 삭제(회원탈퇴시)
	public void deleteByUserId(int userId);
	
	// 글 조회
	public Post selectPostByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	// 글 삭제(타임라인)
	public void deleteByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
}
