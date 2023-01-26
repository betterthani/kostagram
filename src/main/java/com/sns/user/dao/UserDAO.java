package com.sns.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.user.model.User;

@Repository
public interface UserDAO {
	
	public boolean existLoginId(String loginId);
	
	public void insertUserSignupByLoginIdPasswordNameEmail(
			@Param("loginId") String loginId, 
			@Param("password") String password, 
			@Param("name") String name, 
			@Param("email") String email);
	
	public boolean findPasswordByIdNameEmail(
			@Param("loginId") String loginId,
			@Param("email") String email);
	
	public User selectUserByLoginIdPassword(
			@Param("loginId") String loginId, 
			@Param("password") String password);
	
	// 회원탈퇴(조회)
	public int selectUserByPasswordUserId(
			@Param("userId") int userId,
			@Param("password") String password);
	
	// 회원탈퇴 (삭제)
	public void deleteBypasswordUserId(
			@Param("userId") int userId,
			@Param("password") String password);
	
	// 비밀번호 변경(조회)
	public int selectPasswordByloginIdEmail(
			@Param("loginId") String loginId, 
			@Param("email") String email);
	
	// 비밀번호 변경
	public boolean updatePassword(
			@Param("password") String password, 
			@Param("loginId") String loginId);
	
	// 프로필 변경
	public User selectUserByUserId(int userId);
	
	
	// 유저정보(개인페이지)
	public List<User> selectUserListByUserId(int userId);
	
	
	
}
