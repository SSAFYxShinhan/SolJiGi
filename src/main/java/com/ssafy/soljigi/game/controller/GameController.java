package com.ssafy.soljigi.game.controller;

import java.util.List;

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

	@GetMapping
	public String game(Model model) {
		List<QuizDto> quizzes = quizService.getQuizzes(5, 5, null);
		log.info("quizzes={}", quizzes);
		model.addAttribute("quizzes", quizzes);
		model.addAttribute("matchCard", 2);
		model.addAttribute("samePicture", 1);
		return "game/game";
	}

	@PostMapping
	public String addQuiz(@ModelAttribute QuizDto quizDto) {
		quizService.saveQuiz(quizDto);
		return "redirect:/quiz";
	}
}
