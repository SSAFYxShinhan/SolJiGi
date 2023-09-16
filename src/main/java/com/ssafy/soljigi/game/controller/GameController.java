package com.ssafy.soljigi.game.controller;

import java.security.Principal;
import java.util.List;

import com.ssafy.soljigi.base.error.AppException;
import com.ssafy.soljigi.base.error.ErrorCode;
import com.ssafy.soljigi.user.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.soljigi.game.dto.response.QuizDto;
import com.ssafy.soljigi.game.service.QuizService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

	private final QuizService quizService;
	private final UserRepository userRepository;

	@GetMapping
	public String game(Principal principal, Model model) {
		if (principal == null) {
			throw new AppException(ErrorCode.USER_NOT_FOUND);
		}
		String name = principal.getName();
		Long userId = userRepository.findByUsername(name).orElseThrow().getId();

		// userID 설정 변경해야함
		List<QuizDto> quizzes = quizService.getQuizzes(3, 3, userId);
		log.info("quizzes={}", quizzes);
		model.addAttribute("quizzes", quizzes);
		model.addAttribute("matchCard", 2);
		model.addAttribute("samePicture", 1);
		return "game/game";
	}
}
