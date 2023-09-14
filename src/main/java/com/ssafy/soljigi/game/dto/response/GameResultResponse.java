package com.ssafy.soljigi.game.dto.response;

import java.time.LocalDateTime;

import com.ssafy.soljigi.game.entity.GameResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GameResultResponse {

	private long id;
	private int financeCorrect;
	private int financeTotal;
	private int transactionCorrect;
	private int transactionTotal;
	private int matchCardCorrect;
	private int matchCardTotal;
	private int samePictureCorrect;
	private int samePictureTotal;
	private int correctCount;
	private int totalCount;
	private LocalDateTime registrationDate;

	public static GameResultResponse of(GameResult gameResult) {
		return GameResultResponse.builder()
			.id(gameResult.getId())
			.financeCorrect(gameResult.getFinanceCorrect())
			.financeTotal(gameResult.getFinanceTotal())
			.transactionCorrect(gameResult.getTransactionCorrect())
			.transactionTotal(gameResult.getTransactionTotal())
			.matchCardCorrect(gameResult.getMatchCardCorrect())
			.matchCardTotal(gameResult.getMatchCardTotal())
			.samePictureCorrect(gameResult.getSamePictureCorrect())
			.samePictureTotal(gameResult.getSamePictureTotal())
			.correctCount(gameResult.getCorrectCount())
			.totalCount(gameResult.getTotalCount())
			.registrationDate(gameResult.getRegistrationDate())
			.build();
	}
}
