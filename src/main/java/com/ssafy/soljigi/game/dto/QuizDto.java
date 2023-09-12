package com.ssafy.soljigi.game.dto;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.soljigi.game.entity.Quiz;
import com.ssafy.soljigi.game.entity.Type;

import lombok.Builder;
import lombok.Data;

@Data
public class QuizDto {

	private Long id;
	private Type type;
	private String question;
	private List<String> choice;
	private int choiceAnswer;
	private List<String> shortAnswer;

	@Builder
	public QuizDto(Long id, Type type, String question, List<String> choice, int choiceAnswer,
		List<String> shortAnswer) {
		this.id = id;
		this.type = type;
		this.question = question;
		if (choice != null)
			this.choice = new ArrayList<>(choice);
		this.choiceAnswer = choiceAnswer;
		if (shortAnswer != null)
			this.shortAnswer = new ArrayList<>(shortAnswer);
	}

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
