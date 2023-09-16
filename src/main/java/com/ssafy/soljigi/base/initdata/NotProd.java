package com.ssafy.soljigi.base.initdata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.soljigi.api.entity.Account;
import com.ssafy.soljigi.api.entity.Transaction;
import com.ssafy.soljigi.api.repository.AccountRepository;
import com.ssafy.soljigi.api.repository.TransactionRepository;
import com.ssafy.soljigi.diagnosis.dto.request.DiagnosisResultSaveRequest;
import com.ssafy.soljigi.diagnosis.service.DiagnosisResultService;
import com.ssafy.soljigi.game.dto.request.GameResultSaveRequest;
import com.ssafy.soljigi.game.entity.Quiz;
import com.ssafy.soljigi.game.entity.Type;
import com.ssafy.soljigi.game.repository.QuizRepository;
import com.ssafy.soljigi.game.service.GameResultService;
import com.ssafy.soljigi.user.entity.Address;
import com.ssafy.soljigi.user.entity.Gender;
import com.ssafy.soljigi.user.entity.Role;
import com.ssafy.soljigi.user.entity.User;
import com.ssafy.soljigi.user.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotProd {

	private final InitService initService;

	@PostConstruct
	public void init() {
		initService.dbInit();
	}

	@Component
	@Transactional
	@RequiredArgsConstructor
	static class InitService {

		private final QuizRepository quizRepository;
		private final AccountRepository accountRepository;
		private final TransactionRepository transactionRepository;

		private final UserRepository userRepository;
		private final DiagnosisResultService diagnosisResultService;
		private final PasswordEncoder passwordEncoder;
		private final GameResultService gameResultService;

		public void dbInit() {
			int quizCount = 9;

			//			user 생성

			userRepository.save(User.builder()
				.password(passwordEncoder.encode("1"))
				.username("1")
					.name("name")
					.address(new Address("tltl","dododo","cococo"))
					.phoneNumber("123123124")
				.role(Role.USER)
				.gender(Gender.MALE).birthDate(LocalDate.of(1950, 3, 5))
				.accountNumber("110222999995")
				.educationLevel(1)
				.build());
			userRepository.save(User.builder()
				.password(passwordEncoder.encode("1"))
				.username("2")
				.role(Role.USER)
				.gender(Gender.MALE)
				.birthDate(LocalDate.of(1950, 3, 5))
				.accountNumber("110222999993")
				.build());

			// List<Transaction> transactions = Arrays.asList(
			// 	Transaction.builder()
			// 		.transactionDate("20230318")
			// 		.transactionTime("154602")
			// 		.summary("이자")
			// 		.withdraw(1)
			// 		.deposit(1404)
			// 		.content("백화점")
			// 		.balance(331551)
			// 		.inOutType(1)
			// 		.branchName("영업부")
			// 		.account(account)
			// 		.build()
			// );
			// 거래 내역 저장
			//transactionRecordRepository.saveAll(transactions);
		}
	}
}
