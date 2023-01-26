package com.sns.follow;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.follow.bo.FollowBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/follow")
@RestController
public class FollowRestController {
	@Autowired
	private FollowBO followBO;
	
	@GetMapping("/{followerId}")
	public Map<String,Object> follw(@PathVariable int followerId,HttpSession session){
		Integer userId = (Integer) session.getAttribute("userId");
		
		Map<String,Object> result = new HashMap<>();
		// 조회 후 있으면 취소, 없으면 팔로우
		followBO.follwToggle(followerId, userId);
		
		result.put("code", 1);
		result.put("result", "성공");
		return result;
		
	}
}
