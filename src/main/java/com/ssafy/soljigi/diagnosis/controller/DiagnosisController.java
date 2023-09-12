package com.ssafy.soljigi.diagnosis.controller;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.soljigi.base.api.response.ApiResponse;
import com.ssafy.soljigi.diagnosis.dto.request.DiagnosisResultSaveRequest;
import com.ssafy.soljigi.diagnosis.dto.response.DiagnosisResultResponse;
import com.ssafy.soljigi.diagnosis.service.AttentionService;
import com.ssafy.soljigi.diagnosis.service.DiagnosisResultService;
import com.ssafy.soljigi.diagnosis.service.ExecutiveService;
import com.ssafy.soljigi.diagnosis.service.MemoryService;
import com.ssafy.soljigi.diagnosis.service.OrientService;
import com.ssafy.soljigi.sms.dto.SmsResponseDTO;
import com.ssafy.soljigi.sms.service.SmsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/diagnosis")
@RequiredArgsConstructor
public class DiagnosisController {

	private final OrientService orientService;
	private final MemoryService memoryService;
	private final AttentionService attentionService;
	// private final SpacetimeService spacetimeService;
	private final ExecutiveService executiveService;
	// private final LanguageService languageService;
	private final DiagnosisResultService resultService;
	private final SmsService smsService;

	@GetMapping
	public String diagnosis(Model model) {
		Map<String, String> memory4w1h = memoryService.getQuiz4W1H();
		model.addAttribute("orient", orientService.getQuiz());
		model.addAttribute("memory4w1h", memory4w1h);
		model.addAttribute("memory", memoryService.getQuiz(memory4w1h));
		model.addAttribute("attention", attentionService.getQuiz());
		model.addAttribute("spacetime", -1);
		model.addAttribute("executive", executiveService.getQuiz());
		// model.addAttribute("language", languageService.getQuiz());
		return "diagnosis/diagnosis";
	}

	@ResponseBody
	@PostMapping
	public ApiResponse<?> saveResult(@RequestBody DiagnosisResultSaveRequest saveRequest) {
		log.info("saveRequest={}", saveRequest);
		Map<String, Object> data = new HashMap<>();
		try {
			Long savedId = resultService.save(saveRequest);
			data.put("id", savedId);

			SmsResponseDTO response = smsService.sendDiagnosticResult(saveRequest.getUserId(),
				saveRequest.getDiagnosticResult());
			data.put("smsSend", response);
		} catch (IllegalArgumentException e) {
			ApiResponse.ofError("존재하지 않는 회원입니다.");
		}
		// 보호자에게 sms 문자 전송 기능 추가
		return ApiResponse.ofSuccess(data);
	}

	@ResponseBody
	@GetMapping("/{userId}")
	public ApiResponse<?> search(@PathVariable("userId") Long userId) {
		List<DiagnosisResultResponse> data = resultService.findAll(userId);
		return ApiResponse.ofSuccess(data);
	}

}
