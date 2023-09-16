package com.ssafy.soljigi.user.view;

import java.security.Principal;
import java.time.LocalDateTime;

import com.ssafy.soljigi.user.entity.User;
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

	@GetMapping("/game/result")
	public String resultGamePage(Model model, Principal principal) {
		try {
			model.addAttribute("transActionCount", 0);
			if (principal != null) {
				User user = userService.findByUsername(principal.getName());
				TransactionResponse byTransactionCount = userService.findByTransactionCount(user.getAccountNumber());
				model.addAttribute("transActionCount", byTransactionCount.getDataBody().getTransactionCount());
				model.addAttribute("name", user.getName());
				model.addAttribute("point",user.getPoint());
			}
		}catch (Exception e){
			return "error/404";
		}

		return "result/result-game-view";
	}

	@GetMapping("/diagnosis/result")
	public String resultDiagnosisPage(Model model, Principal principal) {
		model.addAttribute("transActionCount", 0);
		if (principal != null) {
			User user = userService.findByUsername(principal.getName());
			TransactionResponse byTransactionCount = userService.findByTransactionCount(user.getAccountNumber());
			model.addAttribute("transActionCount", byTransactionCount.getDataBody().getTransactionCount());
			model.addAttribute("name",user.getName());
			model.addAttribute("point",user.getPoint());
		}

		return "result/result-diagnosis-view";
	}

	@GetMapping("/diagnosis/result/{id}")
	public String resultDiagnosisDetailPage(@PathVariable Long id, Model model, Principal principal) {
		model.addAttribute("id", id);
		if (principal != null) {
			User user = userService.findByUsername(principal.getName());
			DiagnosisResultResponse diagnosisResultResponse = diagnosisResultService.findById(id);
			LocalDateTime date = diagnosisResultResponse.getRegistrationDate();
			Response<?> pattern = userService.findPaymentPatternByTransaction(user.getAccountNumber(), date);
			model.addAttribute("datetime",date.toLocalDate());
			model.addAttribute("transactionResponse", pattern);
			model.addAttribute("name",user.getName());
			model.addAttribute("point",user.getPoint());
		}
		return "result/result-diagnosis-detail-view";
	}

	@GetMapping("/game/result/{id}")
	public String resultGameDetailPage(@PathVariable Long id, Model model, Principal principal) {
		model.addAttribute("id", id);
		if (principal != null) {
			User user = userService.findByUsername(principal.getName());
			GameResultResponse gameResultResponse = gameResultService.findById(id);
			LocalDateTime date = gameResultResponse.getRegistrationDate();
			Response<?> pattern = userService.findPaymentPatternByTransaction(user.getAccountNumber(), date);
			model.addAttribute("datetime",date.toLocalDate());
			model.addAttribute("transactionResponse", pattern);
			model.addAttribute("name",user.getName());
			model.addAttribute("point",user.getPoint());
		}
		return "result/result-game-detail-view";
	}



}
