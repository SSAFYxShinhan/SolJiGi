package com.ssafy.soljigi.dementiaTest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LanguageService {

	static final String[] srcImg = new String[] {
		"https://drive.google.com/file/d/1AQ5GQDW7wP0FjXwAUlQTD3hostHQPGUq/view?usp=drive_link",
		"https://drive.google.com/file/d/1iZZyZU130J8jdKlCO14wMiEcQ7eotOR_/view?usp=drive_link",
		"https://drive.google.com/file/d/1yW6iPQbcS05iqsclN-D5o0BFHse87Ib5/view?usp=drive_link",
		"https://drive.google.com/file/d/1kFCthi8S1PPoaxfr5jLks7x6h54y2MFs/view?usp=drive_link",
		"https://drive.google.com/file/d/19cwp-T-uwY_t1unEU7cX7OacDl4Vm5e8/view?usp=drive_link",
		"https://drive.google.com/file/d/1XepRNRVv2lQdjg3OxbmTW8rIeuVvrDzw/view?usp=drive_link"
	};
	static final String[] answer = new String[] {"바나나", "시소", "칫솔","비행기","사과","태권도"};

	public static HashMap<String, Object> getQuiz(HashMap<String, Object> map) {
		Random random = new Random();

		// Q_img : 이미지로 이름 맞추기
		ArrayList<String> quiz_img = new ArrayList<>();
		int qIndex = random.nextInt(srcImg.length);

		quiz_img.add(srcImg[qIndex]); // src img
		quiz_img.add(answer[qIndex]); // answer

		map.put("q_img", quiz_img);

		return map;
	}
}
