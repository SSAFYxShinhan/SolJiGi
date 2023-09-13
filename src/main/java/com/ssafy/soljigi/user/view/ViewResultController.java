package com.ssafy.soljigi.user.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewResultController {
	@GetMapping("/diagnosis/result")
	public String resultPage() {
		return "result/result-diagnosis-view";
	}

	@GetMapping("/diagnosis/result/detail/{id}")
	public String resultDetailPage(@PathVariable Long id, Model model) {
		model.addAttribute("id",id);
		return "result/result-diagnosis-detail-view";
	}

}
