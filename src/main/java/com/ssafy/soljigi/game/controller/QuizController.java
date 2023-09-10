package com.ssafy.soljigi.game.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.base.error.BaseException;
import com.ssafy.soljigi.game.dto.QuizDto;
import com.ssafy.soljigi.game.service.QuizService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

	private final QuizService quizService;

	@GetMapping("/quizzes")
	public List<QuizDto> getQuizzes() throws BaseException {
		return quizService.getQuizzes(5, 5, 1L);
	}

	@PostMapping("/add")
	public String addQuiz(@ModelAttribute QuizDto quizDto) {
		quizService.saveQuiz(quizDto);
		return "redirect:/quiz";
	}

}
