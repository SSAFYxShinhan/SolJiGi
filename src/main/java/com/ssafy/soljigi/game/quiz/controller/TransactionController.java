package com.ssafy.soljigi.game.quiz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.game.quiz.dto.TransactionResponse;
import com.ssafy.soljigi.game.quiz.service.TransactionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TransactionController {

	private final TransactionService transactionService;

	@GetMapping("/fetchTransaction/{userId}")
	public TransactionResponse fetchTransaction(@PathVariable("userId") Long userId) {
		log.info("userId= {}", userId);
		TransactionResponse response = transactionService.fetchTransactionData(userId);
		transactionService.makeQuizzes(response);
		return transactionService.fetchTransactionData(userId);
	}
}