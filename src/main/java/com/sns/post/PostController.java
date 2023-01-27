package com.sns.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.user.model.UserPage;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostBO postBO;
	
	/**
	 * 글 상세페이지
	 * @param model
	 * @param postId
	 * @param session
	 * @return
	 */
	@GetMapping("/post_detail_view")
	public String postDetailView(Model model,
			@RequestParam("postId") int postId,
			HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/user/sign_in_view";
		}
		
		String userLoginId = (String) session.getAttribute("userLoginId");
		
		Post post = postBO.getPostByPostId(postId);
		model.addAttribute("post",post);
		
		model.addAttribute("viewName", "post/postDetail");
		return"template/layout";
	}
	
	/**
	 * 개인페이지
	 * @param model
	 * @param userId
	 * @param session
	 * @return
	 */
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
		 
		List<UserPage> userPageList = postBO.generateUserPage(userId,sessionId);
		model.addAttribute("userPageList",userPageList);
		
		model.addAttribute("viewName", "post/individualPage");

		return "template/layout";
	}
}
