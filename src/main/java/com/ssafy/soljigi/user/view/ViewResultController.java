package com.ssafy.soljigi.user.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewResultController {
	@GetMapping("/result")
	public String resultPage() {
		return "result/result-view";
	}
}
