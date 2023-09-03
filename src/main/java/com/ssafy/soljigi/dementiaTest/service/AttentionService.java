package com.ssafy.soljigi.dementiaTest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttentionService {
	// 거꾸로 말하기 문제 목록 => DB에 구현 예정
	static final String[] list4Word = new String[] {"금수강산", "오리무중", "진수성찬", "백해무익",
		"건강최고", "백년해로", "사필귀정", "분리수거", "신한은행", "난세영웅"}; // size = 10

	public static HashMap<String, Object> getQuiz(HashMap<String, Object> map) {

		Random random = new Random();

		// Q5 : 주의력 테스트1 - 숫자 바로 따라 말하기 (ex : 6-9-7-3) 4자리
		ArrayList<Integer> quiz = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			quiz.add(random.nextInt(9) + 1);
		}
		map.put("q5", quiz);

		// Q6 : 주의력 테스트1 - 숫자 바로 따라 말하기, 5자리
		quiz = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			quiz.add(random.nextInt(9) + 1);
		}
		map.put("q6", quiz);

		// Q7 : 단어 거꾸로 말하기 (4글자)
		ArrayList<String> quiz7 = new ArrayList<>();
		String fourWord = list4Word[random.nextInt(10)];
		quiz7.add(fourWord);
		quiz7.add(String.valueOf(new StringBuilder(fourWord).reverse()));
		map.put("q7", quiz7);

		return map;

	}
}
