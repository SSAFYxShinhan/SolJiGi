package com.ssafy.soljigi.diagnosis.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.ssafy.soljigi.diagnosis.dto.response.DiagnosisQuizDto;
import com.ssafy.soljigi.diagnosis.entity.DiagnosisType;

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

	public List<DiagnosisQuizDto> getQuiz() {
		Random random = new Random();
		List<DiagnosisQuizDto> quizzes = new ArrayList<>(3);

		int index = random.nextInt(srcImg.length);
		quizzes.add(generateImgQuiz(index));
		index++;
		if(index >= srcImg.length)  index -= srcImg.length;
		quizzes.add(generateImgQuiz(index));
		index++;
		if(index >= srcImg.length)  index -= srcImg.length;
		quizzes.add(generateImgQuiz(index));

		return quizzes;
	}

	private static DiagnosisQuizDto generateImgQuiz(int index) {

		return DiagnosisQuizDto.builder()
			.type(DiagnosisType.LANGUAGE)
			.question("다음 이미지를 보고 무엇인지 말해보세요.<br><img src=" + srcImg[index] + " alt=\"quiz_img\">")
			.shortAnswer(Collections.singletonList(answer[index]))
			.build();
	}
}