package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/comment")
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;
	
	/**
	 * 댓글쓰기 API
	 * @param postId
	 * @param content
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String,Object> commentCreate(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpSession session){
		
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		if(userId == null) {
			result.put("code", 500); // 비로그인
			result.put("result", "error");
			result.put("errorMessage", "로그인을 다시 해주세요.");
			return result;
		}
		
		//db insert
		int row = commentBO.addCommentByUserIdPostIdContent(userId, postId, content);
		if(row > 0) {
			result.put("code", 100); // 성공
			result.put("result", "success");
		} 
		
		return result;
	}
	
	// 댓글 불러오기 API
	
	
}
