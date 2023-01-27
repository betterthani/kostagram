package com.sns.user.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.user.dao.UserDAO;
import com.sns.user.model.User;

@Service
public class UserBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
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
	public List<User> getUserListByUserId(int userId) {
		return userDAO.selectUserListByUserId(userId);
	}
	
	// 프로필 수정
	public void updateUser(String name, String statusMessage, int userId, String userLoginId, String password, MultipartFile file) {
		
		// 기존 유저
		User user = getUserByUserId(userId);
		if(user == null) {
			logger.warn("[update User] 수정할 유저 정보가 없습니다. userId:{}",userId);
			return;
		}
		
		// 수정할게 있을 때
		String profileImgPath = null;
		if(file != null) {
			profileImgPath = fileManagerService.savaFile(userLoginId, file);
			
			if(profileImgPath != null && user.getProfileImgPath() != null) {
				fileManagerService.deleteFile(user.getProfileImgPath());
			}
		}
		
		// db update
		userDAO.updateUser(name, statusMessage, userId, userLoginId, password, profileImgPath);
		
	}
	
}
