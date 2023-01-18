package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, Object> postCreate(
			@RequestParam("content") String content,
			@RequestParam(value= "file", required =false) MultipartFile file,
			HttpSession session){
		
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		Map<String,Object> result = new HashMap<>();
		
		// db insert
		int row = postBO.addPost(userId, userLoginId, content, file);
		if (row > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "게시 실패했습니다. 관리자에게 문의하세요.");
		}
		
		return result;
	}
}