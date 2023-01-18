package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.dao.UserDAO;
import com.sns.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	// 중복확인
	public boolean existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}
	
	// 회원가입
	public void addUserSignupByLoginIdPasswordNameEmail(String loginId, String password, String name, String email) {
		userDAO.insertUserSignupByLoginIdPasswordNameEmail(loginId, password, name, email);
	}
	
	// 비밀번호 찾기
	public boolean findPasswordByIdNameEmail(String loginId, String name, String email) {
		return userDAO.findPasswordByIdNameEmail(loginId, name, email);
	}
	
	// 로그인
	public User getUserByLoginIdPassword(String loginId, String Password) {
		return userDAO.selectUserByLoginIdPassword(loginId, Password);
	}
	
}
