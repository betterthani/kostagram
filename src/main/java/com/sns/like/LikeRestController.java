package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {

	@Autowired
	private LikeBO likeBO;

	// 좋아요,해제 api
	// like?postId=13 @RequestParam
	// like/13 @PathVariable 둘 중 하나 맘대로 해도됨.
	@GetMapping("/like/{postId}")
	public Map<String, Object> like(@PathVariable int postId, HttpSession session) {

		Map<String, Object> result = new HashMap<>();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 500); // 비로그인
			result.put("errorMessage", "로그인을 다시 해주세요.");
			return result;
		}

		// 좋아요 있으면 삭제 / 없으면 추가
		likeBO.likeToggle(postId, userId);

		result.put("code", 1); // 성공
		result.put("result", "성공");
		
		return result;
	}

}
