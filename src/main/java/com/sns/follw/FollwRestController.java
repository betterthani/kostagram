package com.sns.follw;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/follw")
public class FollwRestController {
	
	@GetMapping("/{follwerId}")
	public Map<String,Object> follw(@PathVariable int follwerId,HttpSession session){
		int userId = (int) session.getAttribute("userId");
		Map<String,Object> result = new HashMap<>();
		// 조회 후 있으면 팔로우 취소
		// select db
		
		// insert db
		
		// delete db
		return result;
		
	}
}
