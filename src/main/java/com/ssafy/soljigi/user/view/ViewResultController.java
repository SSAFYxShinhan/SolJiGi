package com.ssafy.soljigi.user.view;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.soljigi.game.dto.response.TransactionResponse;
import com.ssafy.soljigi.user.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
public class ViewResultController {

	private final UserServiceImpl userService;

	@GetMapping("/diagnosis/result")
	public String resultDiagnosisPage(Model model, Principal principal) {
		model.addAttribute("transActionCount", 0);
		if (principal != null) {
			TransactionResponse byTransactionCount = userService.findByTransactionCount(principal.getName());
			model.addAttribute("transActionCount", byTransactionCount.getDataBody().getTransactionCount());
		}

		return "result/result-diagnosis-view";
	}

	@GetMapping("/diagnosis/result/detail/{id}")
	public String resultDiagnosisDetailPage(@PathVariable Long id, Model model) {
		model.addAttribute("id", id);
		return "result/result-diagnosis-detail-view";
	}

	@GetMapping("/game/result")
	public String resultGamePage(Model model, Principal principal) {
		model.addAttribute("transActionCount", 0);
		if (principal != null) {
			TransactionResponse byTransactionCount = userService.findByTransactionCount(principal.getName());
			model.addAttribute("transActionCount", byTransactionCount.getDataBody().getTransactionCount());
		}

		return "result/result-game-view";
	}

}
