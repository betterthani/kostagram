package com.sns.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/timeline")
@Controller
public class TimeLineController {
	
	// localhost:8080/timeline/timeline_view
	// 타임라인 화면
	@GetMapping("/timeline_view")
	public String timelineView(Model model) {
		
		model.addAttribute("viewName","timeline/timeLine");
		
		return "template/layout";
	}
}
