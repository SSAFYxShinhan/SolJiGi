package com.ssafy.soljigi.game.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameResultSaveRequest {

	private int financeCorrect;
	private int financeTotal;
	private int transactionCorrect;
	private int transactionTotal;
	private int matchCardCorrect;
	private int matchCardTotal;
	private int samePictureCorrect;
	private int samePictureTotal;

	public int getCorrectCount() {
		return financeCorrect + transactionCorrect + matchCardCorrect + samePictureCorrect;
	}

	public int getTotalCount() {
		return financeTotal + transactionTotal + matchCardTotal + samePictureTotal;
	}
}
