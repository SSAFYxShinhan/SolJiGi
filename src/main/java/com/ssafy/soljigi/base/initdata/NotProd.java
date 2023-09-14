package com.ssafy.soljigi.base.initdata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import com.ssafy.soljigi.game.dto.request.GameResultSaveRequest;
import com.ssafy.soljigi.game.repository.GameResultRepository;
import com.ssafy.soljigi.game.service.GameResultService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.soljigi.api.entity.Account;
import com.ssafy.soljigi.api.entity.Transaction;
import com.ssafy.soljigi.api.repository.AccountRepository;
import com.ssafy.soljigi.api.repository.TransactionRepository;
import com.ssafy.soljigi.diagnosis.dto.request.DiagnosisResultSaveRequest;
import com.ssafy.soljigi.diagnosis.service.DiagnosisResultService;
import com.ssafy.soljigi.game.entity.Quiz;
import com.ssafy.soljigi.game.entity.Type;
import com.ssafy.soljigi.game.repository.QuizRepository;
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
				.role(Role.USER)
				.gender(Gender.MALE)
				.accountNumber("110222999995")
				.build());
			userRepository.save(User.builder()
				.password(passwordEncoder.encode("1"))
				.username("2")
				.role(Role.USER)
				.gender(Gender.MALE)
				.accountNumber("110222999993")
				.build());

//			diagnosisResultService.save(new DiagnosisResultSaveRequest(
//				1L, 2, 2, 2, 2, 2, 2, 2, 2
//			));
//			diagnosisResultService.save(new DiagnosisResultSaveRequest(
//				1L, 3, 3, 3, 3, 3, 2, 5, 2
//			));
//			diagnosisResultService.save(new DiagnosisResultSaveRequest(
//				2L, 5, 3, 1, 3, 2, 2, 5, 2
//			));
//			diagnosisResultService.save(new DiagnosisResultSaveRequest(
//				1L, 5, 3, 1, 3, 2, 2, 5, 2
//			));


			gameResultService.save(new GameResultSaveRequest(
					1L, 5, 3, 1, 3, 2,2,3,4
			));
			gameResultService.save(new GameResultSaveRequest(
					1L, 3, 3, 1, 3, 2,2,3,4
			));
			gameResultService.save(new GameResultSaveRequest(
					1L, 5, 3, 0, 0, 0,2,3,4
			));
			gameResultService.save(new GameResultSaveRequest(
					1L, 5, 3, 0, 3, 0,2,3,4
			));

			for (int i = 0; i <= quizCount; ++i) {
				quizRepository.save(
					Quiz.builder()
						.type(Type.FINANCE)
						.question("quiz" + i)
						.choice(Arrays.asList("1.aaa", "2.bbb", "3.ccc", "4.ddd"))
						.choiceAnswer(0)
						.build()
				);
			}

			int accountCount = 10;
			int transactionCountPerAccount = 10;
			for (int i = 0; i < accountCount; ++i) {
				Account account = Account.builder()
					.accountNumber("11022299999" + i)
					.productName("저축예금")
					.balance(i * 1000000)
					.customerName("tester" + i)
					.name("tester" + i)
					.gender(Gender.MALE)
					.address(new Address("서울시", "역삼동", "123-456"))
					.birthDate(LocalDate.of(2023, 9, 13))
					.phoneNumber("010-1234-567" + i)
					.build();

				transactionRepository.save(Transaction.builder()
					.account(account)
					.transactionDateTime(LocalDateTime.of(2023, 5, 20, 23, 10))
					.balance(account.getBalance())
					.deposit(0)
					.withdraw(0)
					.inOutType(1)
					.build());

				for (int j = 0; j <= transactionCountPerAccount; ++j) {
					long deposit = (j + 1) * 10000L;
					account.addBalance(deposit);
					transactionRepository.save(Transaction.builder()
						.account(account)
						.transactionDateTime(LocalDateTime.now())
						.balance(account.getBalance())
						.deposit(deposit)
						.withdraw(0)
						.inOutType(1)
						.content("content..." + i + "-" + j)
						.summary("summary..." + i + "-" + j)
						.branchName("branch..." + i + "-" + j)
						.build());
				}
				accountRepository.save(account);
			}

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
