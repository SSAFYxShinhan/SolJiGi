package com.ssafy.soljigi.game.quiz.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.game.quiz.dto.QuizDto;
import com.ssafy.soljigi.game.quiz.entity.Quiz;
import com.ssafy.soljigi.game.quiz.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	private final QuizService quizService;

	public QuizController(QuizService quizService) {
		this.quizService = quizService;
	}

	@GetMapping("/quizzes")
	public List<Quiz> getQuizzes() {
		List<Quiz> quizzes = quizService.getRandomQuizzes();
		return quizzes;
	}

	@GetMapping("/quizzes/{quizId}")
	public String getQuizById(@PathVariable Long quizId, Model model) {
		Quiz quiz = quizService.getQuizById(quizId);
		if (quiz == null) {
			return "game/end";
		}
		model.addAttribute("quiz", quiz);
		return "game/quiz";
	}

	@PostMapping("/add")
	public String addQuiz(@ModelAttribute QuizDto quizDto) {
		quizService.saveQuiz(quizDto);
		return "redirect:/quiz";
	}
}
