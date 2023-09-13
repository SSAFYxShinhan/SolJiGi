package com.ssafy.soljigi.base.initdata;

import java.util.Arrays;
import java.util.List;

import com.ssafy.soljigi.diagnosis.dto.request.DiagnosisResultSaveRequest;
import com.ssafy.soljigi.diagnosis.service.DiagnosisResultService;
import com.ssafy.soljigi.user.entity.Gender;
import com.ssafy.soljigi.user.entity.Role;
import com.ssafy.soljigi.user.entity.User;
import com.ssafy.soljigi.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.soljigi.api.entity.Account;
import com.ssafy.soljigi.api.entity.Transaction;
import com.ssafy.soljigi.api.repository.AccountRepository;
import com.ssafy.soljigi.api.repository.TransactionRepository;
import com.ssafy.soljigi.game.entity.Quiz;
import com.ssafy.soljigi.game.entity.Type;
import com.ssafy.soljigi.game.repository.QuizRepository;

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
		private final TransactionRepository transactionRecordRepository;

		private final UserRepository userRepository;
		private final DiagnosisResultService diagnosisResultService;
		private final PasswordEncoder passwordEncoder;
		public void dbInit() {
			int quizCount = 9;

//			user 생성

			userRepository.save(User.builder()
					.password(passwordEncoder.encode("1"))
					.username("1")
					.role(Role.USER)
					.gender(Gender.MALE)
					.build());

			diagnosisResultService.save(new DiagnosisResultSaveRequest(
					1L,2,2,2,2,2,2,2,2
			));
			diagnosisResultService.save(new DiagnosisResultSaveRequest(
					1L,3,3,3,3,3,2,5,2
			));
			diagnosisResultService.save(new DiagnosisResultSaveRequest(
					1L,5,3,1,3,2,2,5,2
			));

			for (int i = 0; i <= quizCount; ++i) {
				quizRepository.save(
					Quiz.builder()
						.type(Type.CHOICE)
						.question("quiz" + i)
						.choice(Arrays.asList("1.aaa", "2.bbb", "3.ccc", "4.ddd"))
						.choiceAnswer(0)
						.build()
				);
			}

			// 계좌 생성
			Account account = Account.builder()
				.accountNumber("1234")
				.productName("저축예금")
				.balance(331551)
				.customerName("홍길동")
				.build();

			accountRepository.save(account);

			List<Transaction> transactions = Arrays.asList(
				Transaction.builder()
					.transactionDate("20230318")
					.transactionTime("154602")
					.summary("이자")
					.withdraw(1)
					.deposit(1404)
					.content("백화점")
					.balance(331551)
					.inOutType(1)
					.branchName("영업부")
					.account(account)
					.build()
			);

			// 거래 내역 저장
			transactionRecordRepository.saveAll(transactions);
		}
	}
}
