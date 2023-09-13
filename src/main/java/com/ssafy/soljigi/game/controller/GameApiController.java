package com.ssafy.soljigi.game.controller;

import java.util.List;

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
}
