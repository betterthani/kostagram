package com.sns.user.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.bo.FollowBO;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.user.dao.UserDAO;
import com.sns.user.model.User;
import com.sns.user.model.UserPage;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private FollowBO followBO;
	
	// 중복확인
	public boolean existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}
	
	// 회원가입
	public void addUserSignupByLoginIdPasswordNameEmail(String loginId, String password, String name, String email) {
		userDAO.insertUserSignupByLoginIdPasswordNameEmail(loginId, password, name, email);
	}
	
	// 비밀번호 찾기
	public boolean findPasswordByIdNameEmail(String loginId, String email) {
		return userDAO.findPasswordByIdNameEmail(loginId, email);
	}
	
	// 로그인
	public User getUserByLoginIdPassword(String loginId, String Password) {
		return userDAO.selectUserByLoginIdPassword(loginId, Password);
	}
	
	// 회원탈퇴 정보조회
	public int getUserByPasswordUserId(int userId, String password) {
		return userDAO.selectUserByPasswordUserId(userId, password);
	}
	
	// 회원탈퇴
	public void deleteBypasswordUserId(int userId, String password) {
		userDAO.deleteBypasswordUserId(userId, password);
	}
	
	// 비밀번호 변경(조회)
	public int getPasswordByloginIdEmail(String loginId, String email) {
		return userDAO.selectPasswordByloginIdEmail(loginId, email);
	}
	
	// 비밀번호 변경
	public boolean updatePassword(String password, String loginId) {
		return userDAO.updatePassword(password, loginId);
	}
	
	// 프로필 변경
	public User getUserByUserId(int userId) {
		return userDAO.selectUserByUserId(userId);
	}
	
	// 유저정보(개인페이지)
	public List<UserPage> generateUserPage(int userId,Integer sessionId){
		List<UserPage> userPageList = new ArrayList<>();
		
		List<User> userList = userDAO.selectUserListByUserId(userId);
		for(User user : userList) {
			
			UserPage userPage = new UserPage();
			
			// 유저 1명
			userPage.setUser(user);
			
			// 글 여러개 
			List<Post> postList = postBO.getPostListByUserId(user.getId());
			userPage.setPostList(postList);
			
			// 글 카운트
			int count = postBO.getPostCountByUserId(user.getId());
			userPage.setPostCount(count);
			
			// 팔로워 카운트
			int followerCount = followBO.followerCountByFollowerId(user.getId());
			userPage.setFollowerCount(followerCount);
			
			// 팔로잉 카운트 (내가 팔로우한 사람들)
			int followeeCount = followBO.followerCountByUserId(user.getId());
			userPage.setFolloweeCount(followeeCount);
			
			userPageList.add(userPage);
		}
		
		return userPageList;
	}
	
	public List<User> getUserListByUserId(int userId){
		return userDAO.selectUserListByUserId(userId);
	}
}
