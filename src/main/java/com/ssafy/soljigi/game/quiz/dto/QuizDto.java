package com.ssafy.soljigi.game.quiz.dto;

import java.util.List;

import com.ssafy.soljigi.game.quiz.entity.Quiz;
import com.ssafy.soljigi.game.quiz.entity.Type;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizDto {

	private Long id;
	private Type type;
	private String question;
	private List<String> choice;
	private int choiceAnswer;
	private List<String> shortAnswer;

	public static QuizDto of(Quiz quiz) {
		return QuizDto.builder()
			.id(quiz.getId())
			.type(quiz.getType())
			.question(quiz.getQuestion())
			.choice(quiz.getChoice())
			.choiceAnswer(quiz.getChoiceAnswer())
			.shortAnswer(quiz.getShortAnswer())
			.build();
	}
}