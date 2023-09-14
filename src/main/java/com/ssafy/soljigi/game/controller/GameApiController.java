package com.ssafy.soljigi.game.controller;

import java.security.Principal;
import java.util.List;

import com.ssafy.soljigi.base.error.AppException;
import com.ssafy.soljigi.base.error.ErrorCode;
import com.ssafy.soljigi.diagnosis.dto.response.DiagnosisResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.base.api.response.ApiResponse;
import com.ssafy.soljigi.game.dto.request.GameResultSaveRequest;
import com.ssafy.soljigi.game.dto.response.GameResultResponse;
import com.ssafy.soljigi.game.service.GameResultService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameApiController {

	private final GameResultService resultService;

	@PostMapping("/result")
	public ApiResponse<?> saveResult(@RequestBody GameResultSaveRequest saveRequest) {
		log.info("saveRequest={}", saveRequest);
		Long savedId;
		try {
			savedId = resultService.save(saveRequest);
		} catch (IllegalArgumentException e) {
			return ApiResponse.ofError("존재하지 않는 회원입니다.");
		}
		return ApiResponse.ofSuccess(savedId);
	}

	@GetMapping("/result/{userId}")
	public ApiResponse<?> searchResult(@PathVariable("userId") Long userId) {
		List<GameResultResponse> data = resultService.findAll(userId);
		return ApiResponse.ofSuccess(data);
	}

	@GetMapping("/data")
	public ApiResponse<?> searchByUser(Principal principal) {
		if (principal == null)
			throw new AppException(ErrorCode.USER_NOT_FOUND);
		String principalName = principal.getName();
		List<GameResultResponse> data = resultService.findByUserName(principalName);
		return ApiResponse.ofSuccess(data);
	}

	@GetMapping("/data/{id}")
	public ApiResponse<?> searchByUser(@PathVariable Long id) {
		GameResultResponse response = resultService.findById(id);
		return ApiResponse.ofSuccess(response);
	}
}
