package com.ssafy.soljigi.user.view;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.soljigi.diagnosis.dto.response.DiagnosisResultResponse;
import com.ssafy.soljigi.diagnosis.service.DiagnosisResultService;
import com.ssafy.soljigi.game.dto.response.GameResultResponse;
import com.ssafy.soljigi.game.dto.response.TransactionResponse;
import com.ssafy.soljigi.game.service.GameResultService;
import com.ssafy.soljigi.user.dto.response.Response;
import com.ssafy.soljigi.user.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
public class ViewResultController {

	private final UserServiceImpl userService;
	private final DiagnosisResultService diagnosisResultService;
	private final GameResultService gameResultService;

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
	public String resultDiagnosisDetailPage(@PathVariable Long id, Model model, Principal principal) {
		model.addAttribute("id", id);
		if (principal != null) {
			DiagnosisResultResponse diagnosisResultResponse = diagnosisResultService.findById(id);
			LocalDateTime date = diagnosisResultResponse.getRegistrationDate();
			Response<?> pattern = userService.findPaymentPatternByTransaction(principal.getName(), date);
			model.addAttribute("transactionResponse", pattern);
		}
		return "result/result-diagnosis-detail-view";
	}

	@GetMapping("/game/result/detail/{id}")
	public String resultGameDetailPage(@PathVariable Long id, Model model, Principal principal) {
		model.addAttribute("id", id);
		if (principal != null) {
			GameResultResponse gameResultResponse = gameResultService.findById(id);
			LocalDateTime date = gameResultResponse.getRegistrationDate();
			Response<?> pattern = userService.findPaymentPatternByTransaction(principal.getName(), date);
			model.addAttribute("datetime",date.toLocalDate());
			model.addAttribute("transactionResponse", pattern);
		}
		return "result/result-game-detail-view";
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
