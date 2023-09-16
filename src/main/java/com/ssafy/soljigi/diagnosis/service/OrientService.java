package com.ssafy.soljigi.diagnosis.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.soljigi.diagnosis.dto.response.DiagnosisQuizDto;
import com.ssafy.soljigi.diagnosis.entity.DiagnosisType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrientService {
	private static final String[] listDayOfWeek = new String[] {"", "월", "화", "수", "목", "금", "토", "일"};

	public List<DiagnosisQuizDto> getQuiz() {
		List<DiagnosisQuizDto> quizzes = new ArrayList<>(5);

		LocalDate nowTime = LocalDate.now();
		int year = nowTime.getYear();
		int month = nowTime.getMonthValue();
		int day = nowTime.getDayOfMonth();
		int dayOfWeek = nowTime.getDayOfWeek().getValue();

		// Q1-1 : 올해 년도 맞추기
		quizzes.add(DiagnosisQuizDto.builder()
			.type(DiagnosisType.ORIENT)
			.question("올해는 몇 년도입니까?")
			.shortAnswer(getYearAnswerCandidate(year))
			.build());

		// Q1-2 : 오늘의 달 맞추기
		quizzes.add(DiagnosisQuizDto.builder()
			.type(DiagnosisType.ORIENT)
			.question("지금은 몇 월입니까?")
			.shortAnswer(getMonthAnswerCandidate(month))
			.build());

		// Q1-3 : 오늘 일 맞추기
		quizzes.add(DiagnosisQuizDto.builder()
			.type(DiagnosisType.ORIENT)
			.question("오늘은 며칠입니까?")
			.shortAnswer(getDayAnswerCandidate(day))
			.build());

		// Q1-4 : 오늘 일 맞추기
		quizzes.add(DiagnosisQuizDto.builder()
			.type(DiagnosisType.ORIENT)
			.question("오늘은 무슨 요일입니까?")
			.shortAnswer(getDayOfWeekAnswerCandidate(dayOfWeek))
			.build());

		// Q1-5 : 지금 {name}님의 주소는 어디인가요?
		// quizzes.add(DiagnosisQuizDto.builder()
		// 	.type(Type.CHOICE)
		// 	.question("오늘은 무슨 요일입니까?")
		// 	.shortAnswer(getDayOfWeekAnswerCandidate(dayOfWeek))
		// 	.build());

		return quizzes;
	}

	private List<String> getYearAnswerCandidate(int year) {
		List<String> candidate = new ArrayList<>();
		candidate.add(String.valueOf(year));
		candidate.add(String.valueOf(year % 100));
		candidate.add(year + "년");
		candidate.add(year % 100 + "년");
		return candidate;
	}

	private List<String> getMonthAnswerCandidate(int month) {
		List<String> candidate = new ArrayList<>();
		candidate.add(String.valueOf(month));
		candidate.add(month + "월");
		return candidate;
	}

	private List<String> getDayAnswerCandidate(int day) {
		List<String> candidate = new ArrayList<>();
		candidate.add(String.valueOf(day));
		candidate.add(day + "일");
		return candidate;
	}

	private List<String> getDayOfWeekAnswerCandidate(int dayOfWeek) {
		List<String> candidate = new ArrayList<>();
		candidate.add(listDayOfWeek[dayOfWeek]);
		candidate.add(listDayOfWeek[dayOfWeek] + "요일");
		return candidate;
	}
}
