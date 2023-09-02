package com.ssafy.soljigi.game.quiz.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.soljigi.game.quiz.dto.QuizDto;
import com.ssafy.soljigi.game.quiz.entity.Quiz;
import com.ssafy.soljigi.game.quiz.repository.QuizRepository;

@Service
public class QuizService {

	private final QuizRepository quizRepository;

	public QuizService(QuizRepository quizRepository) {
		this.quizRepository = quizRepository;
	}

	public List<Quiz> getAllQuizzes() {
		return quizRepository.findAll();
	}

	public List<Quiz> getRandomQuizzes() {
		List<Quiz> allQuizzes = getAllQuizzes();

		// 리스트를 랜덤하게 섞는다.
		Collections.shuffle(allQuizzes);

		// 처음 10개의 퀴즈만 반환한다.
		return allQuizzes.subList(0, Math.min(10, allQuizzes.size()));
	}

	@Transactional
	public void saveQuiz(QuizDto quizDto) {
		Quiz quiz = Quiz.builder()
			.question(quizDto.getQuestion())
			.options(quizDto.getOptions())
			.answer(quizDto.getAnswer())
			.build();
		quizRepository.save(quiz);
	}

	public Quiz getQuizById(Long id) {
		return quizRepository.findById(id).orElse(null);
	}
}