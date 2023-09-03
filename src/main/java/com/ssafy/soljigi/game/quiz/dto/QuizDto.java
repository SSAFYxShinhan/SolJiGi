package com.ssafy.soljigi.game.quiz.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuizDto {
	private String question;
	private List<String> options;
	private int answer;
}