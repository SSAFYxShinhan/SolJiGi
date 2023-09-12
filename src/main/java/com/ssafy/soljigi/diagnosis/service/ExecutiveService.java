package com.ssafy.soljigi.diagnosis.service;

import java.util.*;

import com.ssafy.soljigi.diagnosis.dto.DiagnosisQuizDto;
import com.ssafy.soljigi.diagnosis.entity.DiagnosisType;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExecutiveService {
	private static final String[] keywordList = new String[] { "과일" };
	Map<String,String> map = Map.of(
		"동그라미", "https://drive.google.com/file/d/1LFxYVWabrFqPol1EcbTjMJqkC4y09c6f/view?usp=drive_link",
		"네모", "https://drive.google.com/file/d/1H6oBrbtq3sFhiSTmTGzxmTmNjJ7ZjgvB/view?usp=drive_link",
		"세모", "https://drive.google.com/file/d/1cB7uyOJsNWMDQVPHsHG--eEdTJz8PY3I/view?usp=drive_link",
		"마름모", "https://drive.google.com/file/d/1DQmGcW4YzQqeepd0SDJIyjNRAyqsOPpP/view?usp=drive_link",
		"별", "https://drive.google.com/file/d/1Lr4uC-PD-x3oHT7-5l5wsTYRe-2ZV2Rw/view?usp=drive_link"
	);

	static final String[] shapeName = new String[] {"동그라미","네모","세모", "마름모", "별"};

	public String getFluencyQuiz() {
		Random random = new Random();

		// Q_exec : 특정 키워드 단어 계속 말하기
		// int qIndex = random.nextInt(keywordList.length);
		int qIndex = 0;

		return keywordList[qIndex];
	}

	public List<DiagnosisQuizDto> getVisualQuiz() {
		List<DiagnosisQuizDto> quizzes = new ArrayList<>(1);
		quizzes.add(generateVirtualQuiz());

		return quizzes;
	}

	private DiagnosisQuizDto generateVirtualQuiz() {
		Random random = new Random();
		List<String> pattern = Arrays.asList(shapeName);
		Collections.shuffle(pattern);
		pattern.remove(4);
		int answer = random.nextInt(3);

		return DiagnosisQuizDto.builder()
			.type(DiagnosisType.EXECUTIVE)
			.question("다음 빈칸에 들어올 그림은 무엇 일까요? + img src 넣기")
			.choice(pattern)
			.choiceAnswer(answer)
			.build();
	}
}