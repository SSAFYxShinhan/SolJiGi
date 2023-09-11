package com.ssafy.soljigi.game.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.base.api.response.ApiResponse;
import com.ssafy.soljigi.game.dto.GameResultSaveRequest;
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
}
