package com.sns.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private PostBO postBO;
	
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
	 * 개인 페이지
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/individual_page_view")
	public String individualPageView(Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/user/sign_in_view";
		}
		model.addAttribute("userId", userId);
		
		// 유저정보(프로필, 아이디, 상태메세지)
		User user = userBO.getUserByUserId(userId);
		model.addAttribute("user", user);
		
		// 게시물 개수 가져오기
		int count = postBO.getPostCountByUserId(userId);
		model.addAttribute("count", count);
		
		// 게시물 내용 갖고오기
		List<Post> postList = postBO.getPostListByUserId(userId);
		model.addAttribute("postList",postList);
		
		model.addAttribute("viewName", "user/individualPage");
		
		return "template/layout";
	}
	
	// 프로필 변경 화면
	@GetMapping("/profile_edit_view")
	public String porfileEditView(Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/user/sign_in_view";
		}
		
		User user = userBO.getUserByUserId(userId);
		model.addAttribute("user", user);
		
		model.addAttribute("viewName", "user/profileEdit");
		return "template/layout";
	}
	
}
