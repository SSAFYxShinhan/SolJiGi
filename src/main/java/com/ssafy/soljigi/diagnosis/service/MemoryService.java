package com.ssafy.soljigi.diagnosis.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ssafy.soljigi.diagnosis.dto.DiagnosisQuizDto;
import com.ssafy.soljigi.diagnosis.entity.DiagnosisType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class MemoryService {
	private static final String[] listWho = new String[] {
		"민수", "영호", "철수", "보람", "영희", "성철", "민지", "영수", "영자", "윤정",
		"준영", "찬호", "상원", "종우", "준범", "석근", "지민", "효진", "수빈", "수현"};
	private static final String[] listHow = new String[] {
		"자전거", "킥보드", "버스", "택시", "지하철",
		"비행기", "오토바이", "트럭", "경찰차", "소방차",
		"구급차", "모범택시", "고속버스", "무궁화호", "유모차"};
	private static final String[] listWhere = new String[] {
		"공원", "마트", "학교", "병원", "경찰서", "소방서", "운동장", "한강", "축구 경기장", "올림픽 공원",
		"강남역", "여의도", "서울역", "서울숲", "학원", "보건소", "세탁소", "식당", "제주도", "울릉도"};
	private static final String[] listWhen = new String[] {
		"1시", "2시", "3시", "4시", "5시", "6시", "7시", "8시", "9시", "10시", "11시", "12시",
		"13시", "14시", "15시", "16시", "17시", "18시", "19시", "20시", "21시", "22시", "23시", "24시"};
	private static final String[] listWhat = new String[] {
		"야구", "축구", "농구", "탁구", "배구", "산책", "테니스", "족구", "당구", "노래",
		"구르기", "줄넘기", "체조", "사격", "공부", "복싱", "골프", "볼링", "공연", "발표"};

	public Map<String, String> getQuiz4W1H() {
		Random random = new Random();
		Map<String, String> map = new HashMap<>();
		map.put("who", listWho[random.nextInt(listWho.length)]);
		map.put("how", listHow[random.nextInt(listHow.length)]);
		map.put("where", listWhere[random.nextInt(listWhere.length)]);
		map.put("when", listWhen[random.nextInt(listWhen.length)]);
		map.put("what", listWhat[random.nextInt(listWhat.length)]);
		return map;
	}

	public List<DiagnosisQuizDto> getQuiz(Map<String, String> _4w1h) {
		List<DiagnosisQuizDto> quizzes = new ArrayList<>(5);
		quizzes.add(generateChoiceQuiz("제가 아까 어떤 사람의 이름을 말했는데 누구일까요?", listWho, _4w1h.get("who")));
		quizzes.add(generateChoiceQuiz("무엇을 타고 갔습니까?", listHow, _4w1h.get("how")));
		quizzes.add(generateChoiceQuiz("어디에 갔습니까?", listWhere, _4w1h.get("where")));
		quizzes.add(generateChoiceQuiz("몇 시부터 했습니까?", listWhen, _4w1h.get("when")));
		quizzes.add(generateChoiceQuiz("무엇을 했습니까?", listWhat, _4w1h.get("what")));
		return quizzes;
	}

	private DiagnosisQuizDto generateChoiceQuiz(String question, String[] candidate, String answer) {
		Random random = new Random();
		Set<String> set = new HashSet<>();
		set.add(answer);
		while (set.size() < 4) {
			set.add(candidate[random.nextInt(candidate.length)]);
		}

		List<String> choices = new ArrayList<>(set.stream().toList());
		Collections.shuffle(choices);

		return DiagnosisQuizDto.builder()
			.type(DiagnosisType.MEMORY)
			.question(question)
			.choice(choices)
			.choiceAnswer(choices.indexOf(answer))
			.build();
	}
}
