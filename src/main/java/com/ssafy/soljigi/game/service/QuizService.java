package com.ssafy.soljigi.game.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.ssafy.soljigi.game.dto.QuizDto;
import com.ssafy.soljigi.game.dto.TransactionResponse;
import com.ssafy.soljigi.game.entity.Quiz;
import com.ssafy.soljigi.game.repository.QuizRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizService {

	// private final String TRANSACTION_URL = "https://shbhack.shinhan.com/v1/search/transaction";
	private final String TRANSACTION_URL = "http://localhost:8080/v1/search/transaction";
	private final QuizRepository quizRepository;

	public List<QuizDto> getQuizzes(int finance, int transaction, Long userId) {
		// List<QuizDto> randomQuizzes = getRandomQuizzes(finance).stream()
		// 	.map(QuizDto::of)
		// 	.toList();
		List<QuizDto> randomQuizzes = new ArrayList<>(getRandomQuizzes(finance).stream().map(QuizDto::of).toList());

		List<QuizDto> transactionQuizzes = makeTransactionQuizzes(userId, transaction);

		randomQuizzes.addAll(transactionQuizzes);
		Collections.shuffle(randomQuizzes);
		return randomQuizzes;
	}

	public List<Quiz> getRandomQuizzes(int count) {
		return quizRepository.findRandomQuizzes(count);
	}

	@Transactional
	public void saveQuiz(QuizDto quizDto) {
		Quiz quiz = Quiz.choiceBuilder()
			.question(quizDto.getQuestion())
			.choice(quizDto.getChoice())
			.choiceAnswer(quizDto.getChoiceAnswer())
			.build();
		quizRepository.save(quiz);
	}

	public List<QuizDto> makeTransactionQuizzes(Long userId, int count) {
		List<TransactionResponse.DataBody.TransactionDetail> details;
		try {
			details = fetchTransactionData(userId);
		} catch (IllegalArgumentException e) {
			return new ArrayList<>();
		}

		List<QuizDto> quizzes = new ArrayList<>();
		for (TransactionResponse.DataBody.TransactionDetail detail : details) {
			String content = detail.getContent();

			quizzes.add(QuizDto.builder()
				.question("최근에 " + content + "(에)서 얼마를 사용하셨나요?")
				.choice(new ArrayList<>(Arrays.asList("0~1999", "2000~4999", "5000~7999", "8000~9999")))
				.choiceAnswer(1)
				.build());

			if (content.equals("김밥천국")) {
				quizzes.add(QuizDto.builder()
					.question("최근에 어디에서 식사를 하셨나요?")
					.choice(new ArrayList<>(Arrays.asList("스타벅스", "김밥천국", "고기집", "횟집")))
					.choiceAnswer(1)
					.build());
			}
		}
		Collections.shuffle(quizzes);

		return quizzes.subList(0, Math.min(count, quizzes.size()));
	}

	public List<TransactionResponse.DataBody.TransactionDetail> fetchTransactionData(Long userId) {
		RestTemplate restTemplate = new RestTemplate();

		/**
		 * TO DO : 회원의 아이디로 계좌번호 가져오기
		 */
		String accountNumber = "110184888888";

		// API 요청에 필요한 데이터 설정 (예시에 따른 요청 본문)
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("dataHeader", Map.of("apikey", "2023_Shinhan_SSAFY_Hackathon"));
		requestBody.put("dataBody", Map.of("계좌번호", accountNumber));

		// API 호출 및 응답 받기
		TransactionResponse response = restTemplate.postForObject(TRANSACTION_URL, requestBody,
			TransactionResponse.class);

		if (response.getDataHeader().getSuccessCode() == 1) {
			throw new IllegalArgumentException();
		}

		return response.getDataBody().getTransactionDetails();
	}
}