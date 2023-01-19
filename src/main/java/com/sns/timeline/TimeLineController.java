package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/timeline")
@Controller
public class TimeLineController {
	
	@Autowired
	private PostBO postBO;
	
	// localhost:8080/timeline/timeline_view
	// 타임라인 화면
	@GetMapping("/timeline_view")
	public String timelineView(Model model,HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/user/sign_in_view";
		}
		List<Post> postList = postBO.getPostByUserId(userId);
		model.addAttribute("postList",postList);
		
		model.addAttribute("viewName","timeline/timeLine");
		
		return "template/layout";
	}
}
