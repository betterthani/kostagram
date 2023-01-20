package com.sns.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostBO postBO;
	
	// 글 상세 페이지
	@GetMapping("/post_detail_view")
	public String postDetailView(Model model,
			@RequestParam("postId") int postId,
			HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/user/sign_in_view";
		}
		
		Post post = postBO.getPostByPostId(postId);
		model.addAttribute("post",post);
		
		model.addAttribute("viewName", "post/postDetail");
		return"template/layout";
	}
}
