package com.ssafy.soljigi.base.initdata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

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

			Random random = new Random();

//			for (int i = 0; i < 12; ++i) {
//				diagnosisResultService.save(
//						1L,
//						new DiagnosisResultSaveRequest(random.nextInt(2) + 4,
//								random.nextInt(2) + 2,
//								random.nextInt(2) + 1,
//								random.nextInt(2) + 5,
//								random.nextInt(3) + 1,
//								random.nextInt(2) + 9)
//				);
//			}
//
//			for (int i = 0; i < 35; ++i) {
//				gameResultService.save(
//						1L,
//						new GameResultSaveRequest(random.nextInt(2) + 4, 5,
//								random.nextInt(2) + 6, 7,
//								random.nextInt(3), 2,
//								random.nextInt(3), 2)
//				);;
//			}

//			userRepository.save(User.builder()
//					.username("test")
//					.password(passwordEncoder.encode("test"))
//					.name("테스터")
//					.address(new Address("서울시 강남구","역삼동","123-456"))
//					.phoneNumber("010-1234-5678")
//					.role(Role.USER)
//					.gender(Gender.MALE)
//					.birthDate(LocalDate.of(1950, 12, 14))
//					.accountNumber("110222999990")
//					.educationLevel(1)
//					.build());

		}
	}
}
