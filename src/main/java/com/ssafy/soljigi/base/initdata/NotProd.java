package com.ssafy.soljigi.base.initdata;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.soljigi.game.quiz.entity.Quiz;
import com.ssafy.soljigi.game.quiz.repository.QuizRepository;

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
		}
	}
}
