package com.ssafy.soljigi.dementiaTest.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TestDto {
	private String question;
	private List<String> options;
	private int answer;
}