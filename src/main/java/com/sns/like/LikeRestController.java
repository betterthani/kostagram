package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {
	
	@Autowired
	private LikeBO likeBO;
	
	// like?postId=13 @RequestParam
	// like/13 @PathVariable 둘 중 하나 맘대로 해도됨.
	@GetMapping("/like/{postId}")
	public Map<String,Object> like(
			@PathVariable int postId, HttpSession session){
		
		Map<String,Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if(userId == null) {
			result.put("code", 500); // 비로그인
			result.put("result", "error");
			result.put("errorMessage", "로그인을 다시 해주세요.");
			return result;
		}
		// 좋아요 누르기 insert
		likeBO.addLikeByUserIdPostId(userId, postId);
		result.put("code", 1);
		result.put("result", "좋아요 누름");
		
		return result;
		
		
		
	}
}
