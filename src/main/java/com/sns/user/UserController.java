package com.sns.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.follow.bo.FollowBO;
import com.sns.post.bo.PostBO;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;
import com.sns.user.model.UserPage;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private FollowBO followBO;
	
	/**
	 * 회원가입 페이지
	 * @return
	 */
	// localhost:8080/user/sign_up_view
	@GetMapping("/sign_up_view")
	public String signUpView(Model model) {
		
		model.addAttribute("viewName", "user/signUp");
		
		return "template/layout";
	}
	
	/**
	 * 로그인 페이지
	 * @param model
	 * @return
	 */
	//localhost:8080/user/sign_in_view
	@GetMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("viewName", "user/signIn");
		
		return "template/layout";
	}
	
	/**
	 * 비밀번호 찾기 페이지
	 */
	//localhost:8080/user/find_password_view
	@GetMapping("/find_password_view")
	public String findPasswordView(Model model) {
		model.addAttribute("viewName", "user/findPassword");
		
		return "template/layout";
	}
	
	/**
	 * 로그아웃
	 * @param session
	 * @return
	 */
	@GetMapping("/sign_out")
	public String signOut(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		return "redirect:/user/sign_in_view";
	}

	/**
	 * 프로필 변경 화면
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/profile_edit_view")
	public String profileEditView(Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/user/sign_in_view";
		}
		
		User user = userBO.getUserByUserId(userId);
		model.addAttribute("user", user);
		
		model.addAttribute("viewName", "user/profileEdit");
		return "template/layout";
	}
	
	
	/**
	 * 개인 페이지
	 * @param model
	 * @param userId
	 * @param session
	 * @return
	 */
	/*
	@GetMapping("/individual_page_view")
	public String individualPageView(
			Model model,
			@RequestParam("userId") int userId, 
			HttpSession session) {
		
		// 로그인된 id
		Integer sessionId = (Integer) session.getAttribute("userId"); 
		if(sessionId == null){ 
		 return "redirect:/user/sign_in_view"; 
		}
		model.addAttribute("sessionId",sessionId);
		 
		//List<UserPage> userPageList = userBO.generateUserPage(userId,sessionId);
		//model.addAttribute("userPageList",userPageList);
		
		// 팔로우 여부 조회
		boolean existFollow = followBO.existFollow(userId, sessionId);
		model.addAttribute("existFollow",existFollow);
		
		
		model.addAttribute("viewName", "user/individualPage");
		
		return "template/layout";
	}
	*/
}
