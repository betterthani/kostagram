package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;
import com.sns.common.EncryptUtils;
import com.sns.post.bo.PostBO;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;
	
	/**
	 * 아이디 중복확인 API
	 * 
	 * @param loginId
	 * @return
	 */
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(@RequestParam("loginId") String loginId) {

		Map<String, Object> result = new HashMap<>();
		boolean isDuplicated = userBO.existLoginId(loginId);
		if (isDuplicated) {
			// 중복일때
			result.put("code", 1);
			result.put("result", true);
		} else {
			// 사용 가능 할때
			result.put("code", 1);
			result.put("result", false);
		}
		return result;
	}

	/**
	 * 회원가입 API
	 * 
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(@RequestParam("loginId") String loginId,
			@RequestParam("password") String password, @RequestParam("name") String name,
			@RequestParam("email") String email) {
		String hashedPassword = EncryptUtils.md5(password);

		userBO.addUserSignupByLoginIdPasswordNameEmail(loginId, hashedPassword, name, email);

		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");

		return result;
	}

	/**
	 * 비밀번호 찾기 API
	 * 
	 * @param loginId
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/find_password")
	public Map<String, Object> findPassword(
			@RequestParam("loginId") String loginId,
			@RequestParam("email") String email) {
		Map<String, Object> result = new HashMap<>();
		// select DB
		boolean findPassword = userBO.findPasswordByIdNameEmail(loginId, email);
		if (findPassword) {
			// 회원정보가 있을때
			result.put("result", true);
			result.put("code", 1);
		} else {
			// 회원정보가 있지 않을 때
			result.put("result", false);
			result.put("code", 1);
		}

		return result;
	}
	
	/**
	 * 비밀번호 변경 API
	 * @param password
	 * @param loginId
	 * @param email
	 * @return
	 */
	@PostMapping("/passwordUpdate")
	public Map<String, Object> userPasswordUpdate(
			@RequestParam("password") String password,
			@RequestParam("loginId") String loginId,
			@RequestParam("email") String email
			){
		
		String hashedPassword = EncryptUtils.md5(password);
		
		Map<String, Object> result = new HashMap<>();
		
		// db select (loginId, email)
		int row = userBO.getPasswordByloginIdEmail(loginId, email);
		
		// db password update
		boolean updatePassword = userBO.updatePassword(hashedPassword, loginId);
		
		if(row > 0) {
			// 조회가능할때
			if(updatePassword) {
				// 업데이트 성공
				result.put("code",1);
			} else {
				// 업데이트 실패
				result.put("code", 500);
			}
		} else {
			// 조회되지 않을때
			result.put("result", "존재하지 않는 ID입니다.");
		}
		
		return result;
	}
	

	/**
	 * 로그인 API
	 * 
	 * @param loginId
	 * @param password
	 * @param session
	 * @return
	 */
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(@RequestParam("loginId") String loginId,
			@RequestParam("password") String password, HttpSession session) {

		// 해싱
		String hashedPassword = EncryptUtils.md5(password);

		// db select
		User user = userBO.getUserByLoginIdPassword(loginId, hashedPassword);

		Map<String, Object> result = new HashMap<>();
		if (user != null) {
			// 로그인
			result.put("code", 1);
			result.put("result", "성공");

			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
		} else {
			// 로그인 실패
			result.put("code", 500);
			result.put("errorMessage", "존재하지 않는 사용자입니다.");
		}

		// return
		return result;

	}

	/*
	 * // 프로필 수정 API
	 * 
	 * @PostMapping("/profileEdit/{userId}") public Map<String, Object> profileEdit(
	 * 
	 * @RequestParam("name") String name,
	 * 
	 * @RequestParam(value="statusMessage",required = false) String statusMessage,
	 * HttpSession session){ Integer userId = (Integer)
	 * session.getAttribute("userId");
	 * 
	 * // update db
	 * 
	 * 
	 * }
	 */

	/**
	 * 회원 탈퇴 API
	 * @param password
	 * @param session
	 * @return
	 */
	@DeleteMapping("/withdrawal")
	public Map<String, Object> userWithdrawal(@RequestParam("password") String password, HttpSession session) {

		int userId = (int) session.getAttribute("userId");

		// 해싱
		String hashedPassword = EncryptUtils.md5(password);

		Map<String, Object> result = new HashMap<>();

		// select db
		int user = userBO.getUserByPasswordUserId(userId, hashedPassword);

		// delete db
		if (user > 0) {
			// 정보가 있을때 삭제
			userBO.deleteBypasswordUserId(userId, hashedPassword);
			postBO.deleteByUserId(userId);
			commentBO.deleteByUserId(userId);
			result.put("code", 1);
		} else {
			// 정보 없으니 삭제 불가
			result.put("errorMessage", "삭제가 불가합니다. 관리자에 문의해주세요.");
		}

		return result;
	}
	

}
