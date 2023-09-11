package com.ssafy.soljigi.game.dto;

import com.ssafy.soljigi.diagnosis.entity.DiagnosisResultType;
import com.ssafy.soljigi.game.entity.GameResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GameResultResponse {

	private int choiceCorrect;
	private int choiceTotal;
	private int shortAnsCorrect;
	private int shortAnsTotal;
	private int matchCardCorrect;
	private int matchCardTotal;
	private int samePictureCorrect;
	private int samePictureTotal;
	private int correctCount;
	private int totalCount;

	public static GameResultResponse of(GameResult gameResult) {
		return GameResultResponse.builder()
			.choiceCorrect(gameResult.getChoiceCorrect())
			.choiceTotal(gameResult.getChoiceTotal())
			.shortAnsCorrect(gameResult.getShortAnsCorrect())
			.shortAnsTotal(gameResult.getShortAnsTotal())
			.matchCardCorrect(gameResult.getMatchCardCorrect())
			.matchCardTotal(gameResult.getMatchCardTotal())
			.samePictureCorrect(gameResult.getSamePictureCorrect())
			.samePictureTotal(gameResult.getSamePictureTotal())
			.correctCount(gameResult.getCorrectCount())
			.totalCount(gameResult.getTotalCount())
			.build();
	}
}
