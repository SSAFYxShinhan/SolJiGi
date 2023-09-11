package com.ssafy.soljigi.diagnosis.service;

import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExecutiveService {

	private static final String[] keywordList = new String[] {
		"과일"};

	public String getFluencyQuiz() {
		Random random = new Random();

		// Q_exec : 특정 키워드 단어 계속 말하기
		// int qIndex = random.nextInt(keywordList.length);
		int qIndex = 0;

		return keywordList[qIndex];
	}

	public Map<String, Object> getVisualQuiz() {


		return null;
	}
}