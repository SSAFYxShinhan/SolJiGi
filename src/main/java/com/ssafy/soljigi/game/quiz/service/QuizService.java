package com.ssafy.soljigi.game.quiz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.soljigi.game.quiz.dto.QuizDto;
import com.ssafy.soljigi.game.quiz.entity.Quiz;
import com.ssafy.soljigi.game.quiz.repository.QuizRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizService {

	private final QuizRepository quizRepository;

	public List<Quiz> getRandomQuizzes() {
		return quizRepository.findRandomQuizzes(10);
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
}