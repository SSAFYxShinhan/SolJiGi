package com.ssafy.soljigi.game.quiz.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.ssafy.soljigi.game.quiz.dto.TransactionResponse;
import com.ssafy.soljigi.game.quiz.entity.Quiz;
import com.ssafy.soljigi.game.quiz.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

	private final TransactionRepository transactionRepository;

	private final String TRANSACTION_URL = "https://shbhack.shinhan.com/v1/search/transaction";

	public void makeQuizzes(TransactionResponse response) {
		List<TransactionResponse.DataBody.TransactionDetail> details = response.getDataBody().getTransactionDetails();

		for (TransactionResponse.DataBody.TransactionDetail detail : details) {
			String content = detail.getContent();

			Quiz quiz = Quiz.choiceBuilder()
				.question("최근에 " + content + "(에)서 얼마를 사용하셨나요?")
				.choice(Arrays.asList("0~1900", "2000~4900", "5000~7900", "8000~9900"))
				.choiceAnswer(1)
				.build();
			saveQuiz(quiz);

			if (content.equals("김밥천국")) {
				Quiz quiz1 = Quiz.choiceBuilder()
					.question("최근에 어디에서 식사를 하셨나요?")
					.choice(Arrays.asList("스타벅스", "김밥천국", "고기집", "횟집"))
					.choiceAnswer(1)
					.build();

				saveQuiz(quiz1);
			}
		}
	}

	@Transactional
	public void saveQuiz(Quiz quiz) {
		transactionRepository.save(quiz);
	}

	public TransactionResponse fetchTransactionData(Long userId) {
		RestTemplate restTemplate = new RestTemplate();

		/**
		 * TO DO : 회원의 아이디로 계좌번호 가져오기
		 */
		String accountNumber = "110184999999";

		// API 요청에 필요한 데이터 설정 (예시에 따른 요청 본문)
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("dataHeader", Map.of("apikey", "2023_Shinhan_SSAFY_Hackathon"));
		requestBody.put("dataBody", Map.of("계좌번호", accountNumber));

		// API 호출 및 응답 받기
		TransactionResponse response = restTemplate.postForObject(TRANSACTION_URL, requestBody,
			TransactionResponse.class);

		if (response.getDataHeader().getSuccessCode() == 1) {
			return null;
		}

		TransactionResponse.DataBody body = response.getDataBody();
		List<TransactionResponse.DataBody.TransactionDetail> details = body.getTransactionDetails();
		for (TransactionResponse.DataBody.TransactionDetail d : details) {
			log.info("d={}", d);
		}

		return response;
	}

}