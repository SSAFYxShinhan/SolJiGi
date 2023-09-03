package com.ssafy.soljigi.dementiaTest.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrientService {
	static final String[] listDayOfWeek = new String[] {"", "월", "화", "수", "목", "금", "토", "일"};
	static int randNum;

	public static HashMap<String, Object> getQuiz(HashMap<String, Object> map) {
		Random random = new Random();

		// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
		LocalDate nowTime = LocalDate.now();
		int year = nowTime.getYear(); // 년, 2023
		int month = nowTime.getMonthValue(); // 월, 6
		int day = nowTime.getDayOfMonth(); // 일, 17
		String dayOfWeek = listDayOfWeek[nowTime.getDayOfWeek().getValue()]; // 요일, 4

		// Q1 : 올해 년도 맞추기
		ArrayList<String> quiz1 = new ArrayList<>();
		randNum = random.nextInt(4);
		year -= randNum;
		quiz1.add(Integer.toString(year)); // option 1
		quiz1.add(Integer.toString(year + 1)); // option 2
		quiz1.add(Integer.toString(year + 2)); // option 3
		quiz1.add(Integer.toString(year + 3)); // option 4
		quiz1.add(Integer.toString(year + randNum)); // answer
		map.put("q1", quiz1);

		// Q2 : 오늘의 달 맞추기
		ArrayList<String> quiz2 = new ArrayList<>();
		randNum = random.nextInt(4);
		month -= randNum;
		if (month <= 0)
			month += 12;
		// option 추가
		for (int i = 0; i < 4; i++) {
			if (month + i > 12) {
				quiz2.add(Integer.toString(month + i - 12));
			} else {
				quiz2.add(Integer.toString(month + i));
			}
		}
		if (month + randNum > 12) {
			quiz2.add(Integer.toString(month + randNum - 12));
		} else {
			quiz2.add(Integer.toString(month + randNum));
		}
		map.put("q2", quiz2);

		// Q3 : 오늘 일  맞추기
		ArrayList<String> quiz3 = new ArrayList<>();
		randNum = random.nextInt(4);
		day -= randNum;
		if (day <= 0)
			day += 31;
		// option 추가
		for (int i = 0; i < 4; i++) {
			if (day + i > 31) {
				quiz3.add(Integer.toString(day + i - 31));
			} else {
				quiz3.add(Integer.toString(day + i));
			}
		}
		if (day + randNum > 31) {
			quiz3.add(Integer.toString(day + randNum - 31));
		} else {
			quiz3.add(Integer.toString(day + randNum));
		}
		map.put("q3", quiz3);

		// Q4 : 오늘의 요일 맞추기
		map.put("q4", dayOfWeek); // only answer
		return map;
	}
}
