package com.ssafy.soljigi.diagnosis.dto.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ssafy.soljigi.diagnosis.entity.DiagnosisQuiz;
import com.ssafy.soljigi.diagnosis.entity.DiagnosisQuizChoice;
import com.ssafy.soljigi.diagnosis.entity.DiagnosisType;

import com.ssafy.soljigi.game.entity.QuizChoice;
import lombok.Builder;
import lombok.Data;

@Data
public class DiagnosisQuizDto {

	private Long id;
	private DiagnosisType type;
	private String question;
	private List<String> choice;
	private int choiceAnswer;
	private List<String> shortAnswer;

	@Builder
	public DiagnosisQuizDto(Long id, DiagnosisType type, String question, List<String> choice, int choiceAnswer,
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

	public static DiagnosisQuizDto of(DiagnosisQuiz quiz) {
		List<DiagnosisQuizChoice> choice = quiz.getChoice();
		Collections.shuffle(choice);

		return DiagnosisQuizDto.builder()
			.id(quiz.getId())
			.type(quiz.getType())
			.question(quiz.getQuestion())
			.choice(choice.stream().map(DiagnosisQuizChoice::getChoiceString).toList())
			.choiceAnswer(findAnswerChoiceIndex(choice))
			.shortAnswer(quiz.getShortAnswer())
			.build();
	}

	private static int findAnswerChoiceIndex(List<DiagnosisQuizChoice> choice) {
		for (int i = 0, size = choice.size(); i < size; ++i) {
			if (choice.get(i).isAnswer()) {
				return i;
			}
		}
		return -1;
	}
}
