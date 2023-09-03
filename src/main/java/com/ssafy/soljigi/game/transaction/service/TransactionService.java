package com.ssafy.soljigi.game.transaction.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ssafy.soljigi.game.transaction.dto.TransactionResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionService {

	private final String TRANSACTION_URL = "https://shbhack.shinhan.com/v1/search/transaction";

	public TransactionResponse fetchTransactionData(Long userId) {
		RestTemplate restTemplate = new RestTemplate();

		/**
		 * TO DO : 회원의 아이디로 계좌번호 가져오기
		 */
		String accountNumber = "110184999999";

		// API 요청에 필요한 데이터 설정 (예시에 따른 요청 본문)
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("dataHeader", Map.of("apikey", "2023_Shinhan_SSAFY_Hackathon"));
		requestBody.put("dataBody", Map.of("계좌번호", accountNumber));

		// API 호출 및 응답 받기
		TransactionResponse response = restTemplate.postForObject(TRANSACTION_URL, requestBody,
			TransactionResponse.class);

		if (response.getDataHeader().getSuccessCode() == 1) {
			return null;
		}

		TransactionResponse.DataBody body = response.getDataBody();
		List<TransactionResponse.DataBody.TransactionDetail> details = body.getTransactionDetails();
		for (TransactionResponse.DataBody.TransactionDetail d : details) {
			log.info("d={}", d);
		}

		return response;
	}

}