package com.sns.follow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.follow.bo.FollowBO;
import com.sns.post.model.FollowerUser;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/follow")
public class FollowController {

	@Autowired
	private FollowBO followBO;

	// 팔로워 리스트
	// localhost:8080/follow/follower_list_view
	@GetMapping("/follower_list_view")
	public String followerListView(
			Model model, 
			@RequestParam("userId") int userId, 
			HttpSession session) {
		int sessionId = (int) session.getAttribute("userId");

		// 팔로우 정보 받아서 model넣기
		List<FollowerUser> FollowerUserInfo = followBO.generateFollowerUserListByFollowerId(userId);
		model.addAttribute("FollowerUserInfo", FollowerUserInfo);

		model.addAttribute("viewName", "follow/followList");

		return "template/layout";
	}

	// 팔로잉 리스트
	// localhost:8080/follow/followee_list_view
	@GetMapping("/followee_list_view")
	public String followeeListView(
			Model model, 
			@RequestParam("userId") int userId) {
		
		//  팔로잉 정보 받아서 model넣기
		List<FollowerUser> FolloweeUserInfo = followBO.generateFolloweeUserListByUserId(userId);
		model.addAttribute("FolloweeUserInfo", FolloweeUserInfo);
		
		model.addAttribute("viewName", "follow/followeeList");

		return "template/layout";
	}

}
