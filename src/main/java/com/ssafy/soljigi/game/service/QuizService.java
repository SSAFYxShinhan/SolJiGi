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

import com.ssafy.soljigi.base.error.BaseException;
import com.ssafy.soljigi.game.dto.response.ChatGptResponse;
import com.ssafy.soljigi.game.dto.response.QuizDto;
import com.ssafy.soljigi.game.dto.response.TransactionResponse;
import com.ssafy.soljigi.game.entity.Quiz;
import com.ssafy.soljigi.game.entity.Type;
import com.ssafy.soljigi.game.repository.QuizRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizService {

	private final ChatGPTService chatGPTService;

	// private final String TRANSACTION_URL = "https://shbhack.shinhan.com/v1/search/transaction";
	private final String TRANSACTION_URL = "http://localhost:8080/v1/search/transaction";
	private final QuizRepository quizRepository;

	public List<QuizDto> getQuizzes(int finance, int transaction, Long userId) {
		List<QuizDto> randomQuizzes = new ArrayList<>(getRandomQuizzes(finance).stream().map(QuizDto::of).toList());
		try {
			List<QuizDto> transactionQuizzes = makeTransactionQuizzes(userId, transaction);
			randomQuizzes.addAll(transactionQuizzes);
		} catch (BaseException ignored) {

		}
		Collections.shuffle(randomQuizzes);
		return randomQuizzes;
	}

	private List<Quiz> getRandomQuizzes(int count) {
		return quizRepository.findRandomQuizzes(count);
	}

	////////////////////// ?
	@Transactional
	public void saveQuiz(QuizDto quizDto) {
		Quiz quiz = Quiz.builder()
			.question(quizDto.getQuestion())
			.choice(quizDto.getChoice())
			.choiceAnswer(quizDto.getChoiceAnswer())
			.build();
		quizRepository.save(quiz);
	}

	private List<QuizDto> makeTransactionQuizzes(Long userId, int count) throws BaseException {
		List<TransactionResponse.DataBody.TransactionDetail> details;

		try {
			details = fetchTransactionData(userId);
			log.info("details={}", details);

		} catch (IllegalArgumentException e) {
			return new ArrayList<>();
		}

		List<QuizDto> quizzes = new ArrayList<>();
		for (TransactionResponse.DataBody.TransactionDetail detail : details) {

			if (detail.getWithdraw() == 0)
				continue;

			String content = detail.getContent();

			// GPT에게 새로운 퀴즈 질문과 선택지를 생성해달라고 요청
			ChatGptResponse chatGptRes = chatGPTService.getChatResponse(
				content + "가 식당이면 " + content + "를 꼭 포함해서 보기 4개를 '|'로 구분해서 만들어줘. 한국어로.\n"
					+ content + "가 식당이 아니면 0만 출력해줘.");

			// GPT 응답에서 질문과 선택지 분리하기
			String[] choices = chatGptRes.getResponseMessage().trim().split("\\|");

			if (choices.length == 4) {
				quizzes.add(QuizDto.builder()
					.type(Type.CHOICE)
					.question("최근에 어디서 식사를 했나요?")
					.choice(new ArrayList<>(Arrays.asList(choices)))
					.choiceAnswer(Arrays.asList(choices).indexOf(content))
					.build());
			}

			quizzes.add(QuizDto.builder()
				.type(Type.CHOICE)
				.question("최근에 " + content + "(에)서 얼마를 사용하셨나요?")
				.choice(new ArrayList<>(Arrays.asList("0~1999", "2000~4999", "5000~7999", "8000~9999")))
				.choiceAnswer(1)
				.build());
		}
		Collections.shuffle(quizzes);
		log.info("quizzes={}", quizzes);

		return quizzes.subList(0, Math.min(count, quizzes.size()));
	}

	private List<TransactionResponse.DataBody.TransactionDetail> fetchTransactionData(Long userId) {
		RestTemplate restTemplate = new RestTemplate();

		/**
		 * TO DO : 회원의 아이디로 계좌번호 가져오기
		 */
		String accountNumber = "1234";

		// API 요청에 필요한 데이터 설정 (예시에 따른 요청 본문)
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("dataHeader", Map.of("apikey", "2023_Shinhan_SSAFY_Hackathon"));
		requestBody.put("dataBody", Map.of("계좌번호", accountNumber));

		// API 호출 및 응답 받기
		TransactionResponse response = restTemplate.postForObject(TRANSACTION_URL, requestBody,
			TransactionResponse.class);

		log.info("fetchTransactionData.response={}", response);

		if (response.getDataHeader().getSuccessCode() == 1) {
			throw new IllegalArgumentException();
		}

		return response.getDataBody().getTransactionDetails();
	}
}