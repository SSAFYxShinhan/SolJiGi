package com.ssafy.soljigi.game.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.ssafy.soljigi.base.error.AppException;
import com.ssafy.soljigi.base.error.BaseException;
import com.ssafy.soljigi.base.error.ErrorCode;
import com.ssafy.soljigi.game.utils.StoreType;
import com.ssafy.soljigi.game.utils.TransactionContentUtils;
import com.ssafy.soljigi.game.dto.response.QuizDto;
import com.ssafy.soljigi.game.dto.response.TransactionResponse;
import com.ssafy.soljigi.game.entity.Quiz;
import com.ssafy.soljigi.game.entity.Type;
import com.ssafy.soljigi.game.repository.QuizRepository;
import com.ssafy.soljigi.user.entity.User;
import com.ssafy.soljigi.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizService {

	private final ChatGPTService chatGPTService;

	// private final String TRANSACTION_URL = "https://shbhack.shinhan.com/v1/search/transaction";
	private final String TRANSACTION_URL = "http://localhost:8080/v1/search/transaction";
	private final UserRepository userRepository;
	private final QuizRepository quizRepository;

	public List<QuizDto> getQuizzes(int finance, int transaction, Long userId) {
		List<QuizDto> quizzes = new ArrayList<>(getFinanceQuizzes(finance).stream().map(QuizDto::of).toList());
		try {
			List<QuizDto> transactionQuizzes = makeTransactionQuizzes(userId, transaction);
			quizzes.addAll(transactionQuizzes);
		} catch (BaseException ignored) {

		}
		Collections.shuffle(quizzes);
		log.info("quizz={}", quizzes);
		return quizzes;
	}

	private List<Quiz> getFinanceQuizzes(int count) {
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
		for (TransactionResponse.DataBody.TransactionDetail transaction : details) {
			if (transaction.getInOutType() == 1) {
				continue;
			}

			QuizDto quiz;
			if ((quiz = generateTransactionPlaceQuiz(transaction)) != null)
				quizzes.add(quiz);
			if ((quiz = generateTransactionDateQuiz(transaction)) != null)
				quizzes.add(quiz);
		}
		Collections.shuffle(quizzes);
		log.info("quizzes={}", quizzes);
		return quizzes.subList(0, Math.min(count, quizzes.size()));
	}

	private QuizDto generateTransactionDateQuiz(TransactionResponse.DataBody.TransactionDetail detail) {
		String content = detail.getContent();
		StringBuilder question = new StringBuilder();
		String date = detail.getTransactionDate();
		StoreType type = TransactionContentUtils.findStoreType(content);

		question.append(content);
		// 장소 → 날짜
		if (type == StoreType.PHARMACY) {
			question.append("에서 언제 약을 구매하셨나요?");
		} else if (type == StoreType.HOSPITAL) {
			question.append("에서 언제 진료를 받으셨나요?");
		} else if (type == StoreType.CAFE) {
			question.append("에서 언제 음료를 드셨나요?");
		} else if (type == StoreType.RESTAURANT) {
			question.append("에서 언제 식사를 하셨나요?");
		} else {
			return null;
		}

		LocalDate answerDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
		List<String> choice = generateRandomDateChoice(answerDate);
		String answer = convertDateFormat(date);
		return QuizDto.builder()
			.type(Type.CHOICE)
			.question(question.toString())
			.choice(choice)
			.choiceAnswer(choice.indexOf(answer))
			.build();
	}

	private QuizDto generateTransactionPlaceQuiz(TransactionResponse.DataBody.TransactionDetail detail) {
		String content = detail.getContent();
		StringBuilder question = new StringBuilder();
		String date = convertDateFormat(detail.getTransactionDate());
		String time = convertTimeFormat(detail.getTransactionTime());
		StoreType type = TransactionContentUtils.findStoreType(content);

		question.append(date).append(" ").append(time);
		// 날짜 → 장소
		if (type == StoreType.PHARMACY) {
			question.append("에<br> 어느 약국을 방문하셨나요?");
		} else if (type == StoreType.HOSPITAL) {
			question.append("에<br> 어느 병원을 다녀오셨나요?");
		} else if (type == StoreType.CAFE) {
			question.append("에<br> 어느 카페를 다녀오셨나요?");
		} else if (type == StoreType.RESTAURANT) {
			question.append("에<br> 어디에서 식사를 하셨나요?");
		} else {
			return null;
		}

		List<String> choice = generateRandomPlaceChoice(type, content);
		return QuizDto.builder()
			.type(Type.CHOICE)
			.question(question.toString())
			.choice(choice)
			.choiceAnswer(choice.indexOf(content))
			.build();
	}

	private List<TransactionResponse.DataBody.TransactionDetail> fetchTransactionData(Long userId) {
		RestTemplate restTemplate = new RestTemplate();

		/**
		 * TO DO : 회원의 아이디로 계좌번호 가져오기
		 */
		User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
		String accountNumber = user.getAccountNumber();

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

	private static List<String> generateRandomDateChoice(LocalDate dateTime) {
		List<String> list = new ArrayList<>(4);
		int left = new Random().nextInt(4);
		for (int i = left; i >= 0; --i) {
			list.add(dateTime.minusDays(i).format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
		}
		for (int i = 1; i < 4 - left; ++i) {
			list.add(dateTime.plusDays(i).format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
		}
		return list;
	}

	private static List<String> generateRandomPlaceChoice(StoreType type, String answer) {
		List<String> list = new ArrayList<>(4);
		list.add(answer);
		while (list.size() < 4) {
			String choice = TransactionContentUtils.getRandomChoice(type);
			if (!list.contains(choice))
				list.add(choice);
		}
		return list;
	}

	private static String convertDateFormat(String date) {
		return date.substring(0, 4) + "년 "
			+ date.substring(4, 6) + "월 "
			+ date.substring(6, 8) + "일";
	}

	private static String convertTimeFormat(String time) {
		if (time.startsWith("00", 2)) {
			return time.substring(0, 2) + "시";
		} else {
			return time.substring(0, 2) + "시 "
				+ time.substring(2, 4) + "분";
		}
	}
}