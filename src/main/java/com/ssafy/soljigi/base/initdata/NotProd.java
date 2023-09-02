package com.ssafy.soljigi.base.initdata;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.soljigi.game.quiz.dto.QuizDto;
import com.ssafy.soljigi.game.quiz.service.QuizService;

@Configuration
public class NotProd {

	@Bean
	public CommandLineRunner initData(
		QuizService quizService
	) {
		return new CommandLineRunner() {

			@Override
			@Transactional
			public void run(String... args) throws Exception {
				// 첫 번째 퀴즈 데이터 생성
				QuizDto quiz1 = QuizDto.builder()
					.question("퇴직 시기에 받을 수 있는 정기적인 금액을 보장하는 금융 상품은 무엇일까요?")
					.options(Arrays.asList("연금", "주식", "실업 보험", "은퇴 계좌"))
					.answer(0)
					.build();

				// 두 번째 퀴즈 데이터 생성
				QuizDto quiz2 = QuizDto.builder()
					.question("월말에 받는 정기적인 돈으로 생활비를 지출하면서 남는 돈을 모아놓는 것을 뭐라고 할까요?")
					.options(Arrays.asList("용돈", "퇴직금", "저축", "대출"))
					.answer(2)
					.build();

				// 첫 번째 퀴즈 데이터 생성
				QuizDto quiz3 = QuizDto.builder()
					.question("어떤 상품을 사고 팔아서 차액으로 이익을 얻는 행위를 뭐라고 할까요?")
					.options(Arrays.asList("교환", "투자", "대출", "선물거래"))
					.answer(1)
					.build();

				// 두 번째 퀴즈 데이터 생성
				QuizDto quiz4 = QuizDto.builder()
					.question("평소에 살고 있는 집을 파는 것을 뭐라고 할까요?")
					.options(Arrays.asList("가계 대출", "투자", "주식", "부동산 매매"))
					.answer(3)
					.build();

				// 퀴즈 데이터 저장
				quizService.saveQuiz(quiz1);
				quizService.saveQuiz(quiz2);
				quizService.saveQuiz(quiz3);
				quizService.saveQuiz(quiz4);
			}
		};
	}
}
