package com.ssafy.soljigi.game.controller;

import java.security.Principal;
import java.util.List;
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
	private final UserRepository userRepository;

	@PostMapping("/result")
	public ApiResponse<?> saveResult(Principal principal,
									 @RequestBody GameResultSaveRequest saveRequest) {
		String name = principal.getName();
		Optional<User> optional = userRepository.findByUsername(name);
		if (optional.isEmpty()) {
			return ApiResponse.ofError("존재하지 않는 회원입니다.");
		}
		Long userId = optional.get().getId();
		Long savedId;
		try {
			savedId = resultService.save(userId, saveRequest);
		} catch (IllegalArgumentException e) {
			return ApiResponse.ofError("존재하지 않는 회원입니다.");
		}
		return ApiResponse.ofSuccess(savedId);
	}

	@GetMapping("/result")
	public ApiResponse<?> searchResult(Principal principal) {
		String name = principal.getName();
		Optional<User> optional = userRepository.findByUsername(name);
		if (optional.isEmpty()) {
			return ApiResponse.ofError("존재하지 않는 회원입니다.");
		}
		Long userId = optional.get().getId();
		List<GameResultResponse> data = resultService.findAll(userId);
		return ApiResponse.ofSuccess(data);
	}
}
