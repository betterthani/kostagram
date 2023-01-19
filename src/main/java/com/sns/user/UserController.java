package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {
	
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
		String userLoginId = (String) session.getAttribute("userLoginId");
		if(userLoginId == null) {
			return "redirect:/user/sign_in_view";
		}
		model.addAttribute("userLoginId", userLoginId);
		model.addAttribute("viewName", "user/individualPage");
		
		return "template/layout";
	}
	
}
