package com.ssafy.soljigi.diagnosis.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ssafy.soljigi.user.entity.User;
import com.ssafy.soljigi.user.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.base.api.response.ApiResponse;
import com.ssafy.soljigi.base.error.AppException;
import com.ssafy.soljigi.base.error.ErrorCode;
import com.ssafy.soljigi.diagnosis.dto.request.DiagnosisResultSaveRequest;
import com.ssafy.soljigi.diagnosis.dto.response.DiagnosisResultResponse;
import com.ssafy.soljigi.diagnosis.service.DiagnosisResultService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/diagnosis")
public class DiagnosisApiController {
	private final DiagnosisResultService resultService;
	private final UserRepository userRepository;

	// private final SmsService smsService;
	@PostMapping
	public ApiResponse<?> saveResult(Principal principal,
									 @RequestBody DiagnosisResultSaveRequest saveRequest) {

		if (principal == null) {
			throw new AppException(ErrorCode.USER_NOT_FOUND);
		}
		String name = principal.getName();
		Long userId = userRepository.findByUsername(name).orElseThrow().getId();

		Map<String, Object> data = new HashMap<>();
		try {
			Long savedId = resultService.save(userId, saveRequest);
			data.put("id", savedId);

			//			SmsResponseDTO response = smsService.sendDiagnosticResult(saveRequest.getUserId(),
			//				saveRequest.getDiagnosticResult());
			//			data.put("smsSend", response);
		} catch (IllegalArgumentException e) {
			ApiResponse.ofError("존재하지 않는 회원입니다.");
		}
		// 보호자에게 sms 문자 전송 기능 추가
		return ApiResponse.ofSuccess(data);
	}

	@GetMapping("/result")
	public ApiResponse<?> search(Principal principal) {
		if (principal == null) {
			throw new AppException(ErrorCode.USER_NOT_FOUND);
		}
		String name = principal.getName();
		Long userId = userRepository.findByUsername(name).orElseThrow().getId();
		List<DiagnosisResultResponse> data = resultService.findAll(userId);
		return ApiResponse.ofSuccess(data);
	}

	@GetMapping("/data")
	public ApiResponse<?> searchByUser(Principal principal) {
		if (principal == null)
			throw new AppException(ErrorCode.USER_NOT_FOUND);
		String principalName = principal.getName();
		List<DiagnosisResultResponse> data = resultService.findByUserName(principalName);
		return ApiResponse.ofSuccess(data);
	}

	@GetMapping("/data/{id}")
	public ApiResponse<?> searchByUser(@PathVariable Long id) {
		DiagnosisResultResponse response = resultService.findById(id);
		return ApiResponse.ofSuccess(response);
	}

}
