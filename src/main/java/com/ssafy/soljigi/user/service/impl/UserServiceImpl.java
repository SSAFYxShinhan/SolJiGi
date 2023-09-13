package com.ssafy.soljigi.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ssafy.soljigi.base.error.AppException;
import com.ssafy.soljigi.base.error.ErrorCode;
import com.ssafy.soljigi.game.dto.response.TransactionResponse;
import com.ssafy.soljigi.user.entity.User;
import com.ssafy.soljigi.user.repository.UserRepository;
import com.ssafy.soljigi.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	private final String TRANSACTION_URL = "http://localhost:8080";

	public User findByUsername(String username) {
		return userRepository.findByUsername(username)
			.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
	}

	public TransactionResponse findByTransactionCount(String username) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
		String accountNumber = user.getAccountNumber();

		// API 요청에 필요한 데이터 설정 (예시에 따른 요청 본문)
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("dataHeader", Map.of("apikey", "2023_Shinhan_SSAFY_Hackathon"));
		requestBody.put("dataBody", Map.of("계좌번호", accountNumber));

		// API 호출 및 응답 받기
		RestTemplate restTemplate = new RestTemplate();
		TransactionResponse response = restTemplate.postForObject(TRANSACTION_URL + "/v1/search/transaction",
			requestBody,
			TransactionResponse.class);

		if (response.getDataHeader().getSuccessCode() == 1) {
			throw new IllegalArgumentException();
		}

		return response;
	}

	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) {
				return userRepository.findByUsername(username)
					.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
			}
		};
	}
}
