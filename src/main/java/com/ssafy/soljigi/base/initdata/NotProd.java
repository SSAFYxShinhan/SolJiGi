package com.ssafy.soljigi.base.initdata;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.soljigi.api.entity.Account;
import com.ssafy.soljigi.api.entity.Transaction;
import com.ssafy.soljigi.api.repository.AccountRepository;
import com.ssafy.soljigi.api.repository.TransactionRepository;
import com.ssafy.soljigi.game.entity.Quiz;
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

		public void dbInit() {
			int quizCount = 9;

			for (int i = 0; i <= quizCount; ++i) {
				quizRepository.save(
					Quiz.choiceBuilder()
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
