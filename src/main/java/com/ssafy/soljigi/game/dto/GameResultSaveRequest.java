package com.ssafy.soljigi.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameResultSaveRequest {

	private Long userId;
	private int choiceCorrect;
	private int choiceTotal;
	private int shortAnsCorrect;
	private int shortAnsTotal;
	private int matchCardCorrect;
	private int matchCardTotal;
	private int samePictureCorrect;
	private int samePictureTotal;

	public int getCorrectCount() {
		return choiceCorrect + shortAnsCorrect + matchCardCorrect + samePictureCorrect;
	}

	public int getTotalCount() {
		return choiceTotal + shortAnsTotal + matchCardTotal + samePictureTotal;
	}
}
