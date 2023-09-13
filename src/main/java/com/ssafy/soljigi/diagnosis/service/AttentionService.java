package com.ssafy.soljigi.diagnosis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.ssafy.soljigi.diagnosis.dto.DiagnosisQuizDto;
import com.ssafy.soljigi.diagnosis.entity.DiagnosisType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttentionService {
	// 거꾸로 말하기 문제 목록 => DB에 구현 예정
	private static final String[] list4Word = new String[] {
		"금수강산", "오리무중", "진수성찬", "백해무익", "건강최고",
		"백년해로", "사필귀정", "분리수거", "신한은행", "난세영웅"};
	private static final String[][] numCandidate = {
		{},
		{"일", "하나", "1", "원"},
		{"이", "둘", "2", "투"},
		{"삼", "셋", "3", "쓰리"},
		{"사", "넷", "4", "포"},
		{"오", "다섯", "5", "파이브"},
		{"육", "여섯", "6", "식스"},
		{"칠", "일곱", "7", "세븐"},
		{"팔", "여덟", "8", "에잇"},
		{"구", "아홉", "9", "나인"}};

	public List<DiagnosisQuizDto> getQuiz() {
		List<DiagnosisQuizDto> quizzes = new ArrayList<>(3);
		quizzes.add(generateNumberQuiz(4));
		quizzes.add(generateNumberQuiz(5));
		quizzes.add(generateReverseQuiz());
		return quizzes;
	}

	private DiagnosisQuizDto generateReverseQuiz() {
		Random random = new Random();
		String word = list4Word[random.nextInt(list4Word.length)];
		ArrayList<String> keyword = new ArrayList<>();
		keyword.add(word);
		return DiagnosisQuizDto.builder()
			.question("제가 불러드리는 말을 끝에서부터 거꾸로 따라해주세요.")
			.shortAnswer(List.of(new StringBuffer(word).reverse().toString()))
			.choice(keyword)
			.build();
	}

	private DiagnosisQuizDto generateNumberQuiz(int count) {
		Random random = new Random();
		StringBuilder problem = new StringBuilder();
		StringBuilder answer = new StringBuilder();
		int[] nums = new int[count];

		for (int i = 0; i < count; ++i) {
			nums[i] = random.nextInt(9) + 1;
			problem.append(nums[i]).append(",");

			answer.append(nums[i]);
		}
		ArrayList<String> keyword = new ArrayList<>();
		keyword.add(problem.toString());
		return DiagnosisQuizDto.builder()
			.type(DiagnosisType.ATTENTION)
			.question("제가 불러드리는 숫자를 그대로 따라해주세요.")
			.shortAnswer(getNumberQuizAnswerCandidate(answer.toString()))
			.choice(keyword)
			.build();
	}

	private List<String> getNumberQuizAnswerCandidate(String answer) {
		List<String> candidate = new ArrayList<>();
		for (int i = 0; i < 4; ++i) {
			StringBuilder sb = new StringBuilder();
			for (char c: answer.toCharArray()) {
				sb.append(numCandidate[c - '0'][i]);
			}
			candidate.add(sb.toString());
		}
		return candidate;
	}
}
